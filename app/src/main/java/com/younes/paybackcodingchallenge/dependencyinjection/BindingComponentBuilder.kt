package com.younes.paybackcodingchallenge.dependencyinjection

import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface BindingComponentBuilder {
    fun build(): BindingComponent
}