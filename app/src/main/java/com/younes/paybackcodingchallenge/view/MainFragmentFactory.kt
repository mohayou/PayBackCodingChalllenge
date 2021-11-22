package com.younes.paybackcodingchallenge.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.younes.paybackcodingchallenge.adapter.ImageRecyclerViewAdapter
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerViewAdapter,
    private val glide : RequestManager,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            SearchFragment::class.java.name -> SearchFragment(imageRecyclerAdapter)
            ImageDetailFragment::class.java.name -> ImageDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}