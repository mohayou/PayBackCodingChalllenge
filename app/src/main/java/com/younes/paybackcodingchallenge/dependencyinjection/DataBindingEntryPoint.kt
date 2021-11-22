package com.younes.paybackcodingchallenge.dependencyinjection

import com.younes.paybackcodingchallenge.adapter.ImageRecyclerViewAdapter
import com.younes.paybackcodingchallenge.view.ImageDetailFragment
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@BindingScope
@InstallIn(BindingComponent::class)
interface DataBindingEntryPoint {

    @BindingScope
    fun getImageRecyclerViewAdapter() : ImageRecyclerViewAdapter


    @BindingScope
    fun getImageDetailFragment() : ImageDetailFragment
}