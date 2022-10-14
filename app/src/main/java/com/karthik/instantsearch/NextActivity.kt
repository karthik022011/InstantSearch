package com.karthik.instantsearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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

class NextActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

    }


}