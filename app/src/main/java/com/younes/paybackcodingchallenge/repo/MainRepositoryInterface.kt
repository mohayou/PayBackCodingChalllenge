package com.younes.paybackcodingchallenge.repo

import androidx.lifecycle.LiveData
import com.younes.paybackcodingchallenge.model.ImageResponse
import com.younes.paybackcodingchallenge.model.ImageResult
import com.younes.paybackcodingchallenge.util.Resource

interface MainRepositoryInterface {

    suspend fun inserImages(imageResults: List<ImageResult>)

    fun getImageFromDb(imageId:String) : LiveData<ImageResult>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>

}