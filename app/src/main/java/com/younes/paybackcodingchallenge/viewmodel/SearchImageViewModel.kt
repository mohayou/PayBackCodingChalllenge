package com.younes.paybackcodingchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.younes.paybackcodingchallenge.model.ImageResponse
import com.younes.paybackcodingchallenge.model.ImageResult
import com.younes.paybackcodingchallenge.repo.MainRepositoryInterface
import com.younes.paybackcodingchallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchImageViewModel @Inject constructor(
        private val repository : MainRepositoryInterface
) : ViewModel() {

        private val images = MutableLiveData<Resource<ImageResponse>>()
        val imageList : LiveData<Resource<ImageResponse>>
                get() = images

        fun searchForImage (searchString : String) {
                images.value = Resource.loading(null)
                viewModelScope.launch {
                        val response = repository.searchImage(searchString)
                        images.value = response
                }
        }

        fun insertImages(imageList:List<ImageResult>)
        {
                viewModelScope.launch {
                        repository.inserImages(imageList)
                }
        }


}