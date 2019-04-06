package com.tjohnn.footballfixtures.di

import android.app.Application
import com.tjohnn.footballfixtures.App
import com.tjohnn.footballfixtures.di.activitybinding.ActivityBindingModule
import com.tjohnn.footballfixtures.di.viewmodelbinding.ViewModelBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class,
    NetworkModule::class, ViewModelBindingModule::class, RoomModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}