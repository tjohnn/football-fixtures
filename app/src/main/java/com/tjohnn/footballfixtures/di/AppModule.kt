package com.tjohnn.footballfixtures.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule(private val app : Application){

    @Binds
    abstract fun providesApp(app: Application) : Context

}