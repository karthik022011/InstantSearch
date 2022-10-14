package com.karthik.instantsearch.adapter

import com.karthik.instantsearch.data.response.Hit

interface ImageListHandler {
    fun onModelClicked(data: Hit)
}