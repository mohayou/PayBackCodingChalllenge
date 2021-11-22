package com.younes.paybackcodingchallenge.dependencyinjection

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

@Scope
@Retention(BINARY)
annotation class BindingScope