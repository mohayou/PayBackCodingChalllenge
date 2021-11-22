package com.younes.paybackcodingchallenge

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.younes.paybackcodingchallenge.dependencyinjection.BindingComponentBuilder
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class PayBackApplication : Application() {

    @Inject
    lateinit var bindingComponentProvider: Provider<BindingComponentBuilder>

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(bindingComponentProvider.get().build())
    }

}