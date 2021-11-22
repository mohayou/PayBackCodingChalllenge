package com.younes.paybackcodingchallenge.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.younes.paybackcodingchallenge.api.RetrofitAPI
import com.younes.paybackcodingchallenge.model.ImageResponse
import com.younes.paybackcodingchallenge.model.ImageResult
import com.younes.paybackcodingchallenge.roomdb.ImageDao
import com.younes.paybackcodingchallenge.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor (
    private val artDao : ImageDao,
    private val retrofitApi : RetrofitAPI
) : MainRepositoryInterface {

    override suspend fun inserImages(imageResults: List<ImageResult>) {
        artDao.insertImages(imageResults)
    }

    override fun getImageFromDb(imageId:String): LiveData<ImageResult> {
        return artDao.getImageFromDb(imageId)
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitApi.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("",null)
                } else {
                    Resource.error("",null)
            }
        } catch (e: Exception) {
            Log.d("testEception",e.toString())
            Resource.error("No data!",null)
        }
    }


}