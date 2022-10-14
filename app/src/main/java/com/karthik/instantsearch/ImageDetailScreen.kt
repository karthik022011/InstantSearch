package com.karthik.instantsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.karthik.instantsearch.data.response.Hit
import com.karthik.instantsearch.databinding.ActivityImageDetailScreenBinding

class ImageDetailScreen : AppCompatActivity() {
    private lateinit var viewBinding: ActivityImageDetailScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail_screen)
        viewBinding = ActivityImageDetailScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
     val imageDetails: Hit? =   intent.getParcelableExtra("ImageDetails")
        Glide.with(viewBinding.ivImage.context)
            .load(imageDetails?.previewURL)
            .into(viewBinding.ivImage)

        viewBinding.tvUserName.text = imageDetails?.user
        viewBinding.tvImageTags.text = imageDetails?.tags


     viewBinding.buttonShowToast.setOnClickListener {
         startActivity(Intent(this, NextActivity::class.java))
     }
    }


}