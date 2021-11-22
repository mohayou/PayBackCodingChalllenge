package com.younes.paybackcodingchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.younes.paybackcodingchallenge.model.ImageResponse
import com.younes.paybackcodingchallenge.model.ImageResult
import com.younes.paybackcodingchallenge.repo.MainRepositoryInterface
import com.younes.paybackcodingchallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository : MainRepositoryInterface
) : ViewModel() {

    var imagedetails: LiveData<ImageResult> = MutableLiveData<ImageResult>()

    //    fun getImageFromDb(imageId:String):LiveData<ImageResult> = repository.getImageFromDb(imageId)
    fun getImageFromDb(imageId: String) {
        imagedetails =  repository.getImageFromDb(imageId)
}
}