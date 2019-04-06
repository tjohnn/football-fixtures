package com.tjohnn.footballfixtures

import com.tjohnn.footballfixtures.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    lateinit var injector: AndroidInjector<out DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return injector
    }

    override fun onCreate() {
        injector = DaggerAppComponent.builder().application(this).build()
        super.onCreate()

    }
}