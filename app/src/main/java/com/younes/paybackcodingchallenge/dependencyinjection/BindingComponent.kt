package com.younes.paybackcodingchallenge.dependencyinjection

import androidx.databinding.DataBindingComponent
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@BindingScope
@DefineComponent(parent = SingletonComponent::class)
interface BindingComponent : DataBindingComponent