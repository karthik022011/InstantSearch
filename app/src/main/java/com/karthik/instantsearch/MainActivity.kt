package com.karthik.instantsearch

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.karthik.instantsearch.BuildConfig
import com.karthik.instantsearch.adapter.ImageListAdapter
import com.karthik.instantsearch.adapter.ImageListHandler
import com.karthik.instantsearch.adapter.ImageStateAdapter
import com.karthik.instantsearch.data.APIService
import com.karthik.instantsearch.data.response.Hit
import com.karthik.instantsearch.databinding.ActivityMainBinding
import com.karthik.instantsearch.viewmodel.MainActivityViewModel
import com.karthik.instantsearch.viewmodel.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var   viewModel: MainActivityViewModel

    private val recyclerViewAdapter: ImageListAdapter by lazy {
        ImageListAdapter(object : ImageListHandler{
            override fun onModelClicked(data: Hit) {
                val intent = Intent(this@MainActivity, ImageDetailScreen::class.java)
                intent.putExtra("ImageDetails", data)
                startActivity(intent)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initRecyclerView()
        initViewModel()
        setUpSearchStateFlow()
        Toast.makeText(this,  BuildConfig.BASE_URL, Toast.LENGTH_LONG).show()

    }

    private fun initRecyclerView() {
        viewBinding.rvImageSearchList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
       val  imageStateAdapter = ImageStateAdapter { recyclerViewAdapter.retry() }
        viewBinding.rvImageSearchList.adapter = recyclerViewAdapter.withLoadStateFooter(imageStateAdapter)
    }

    private fun initViewModel(){
         viewModel  = ViewModelProvider(this@MainActivity,
            MainViewModelFactory(APIService.getApiService())).get(MainActivityViewModel::class.java)
    }

    private fun setUpSearchStateFlow() {
        lifecycleScope.launch {
            viewBinding.svImage.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter { query ->
                    if (query.isEmpty()) {
                        viewBinding.tvNoImagesFound.isVisible = query.isEmpty()
                        return@filter false
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    viewModel.getListData(query)
                }
                .flowOn(Dispatchers.Default)
                .collectLatest {
                    recyclerViewAdapter.submitData(it)
                }
        }
    }

}