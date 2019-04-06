package com.tjohnn.footballfixtures.di.activitybinding

import android.support.v7.app.AppCompatActivity
import com.tjohnn.footballfixtures.ui.MainActivity
import com.tjohnn.footballfixtures.di.annotations.ActivityScoped
import com.tjohnn.footballfixtures.di.annotations.FragmentScoped
import com.tjohnn.footballfixtures.ui.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment


    @ActivityScoped
    @Binds
    abstract fun activity(mainActivity: MainActivity): AppCompatActivity
}
