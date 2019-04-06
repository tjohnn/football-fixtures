package com.tjohnn.footballfixtures.di.activitybinding


import com.tjohnn.footballfixtures.di.annotations.ActivityScoped
import com.tjohnn.footballfixtures.ui.MainActivity
import com.tjohnn.footballfixtures.ui.leagues.LeaguesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LeaguesActivityModule::class])
    internal abstract fun leaguesActivity(): LeaguesActivity

}
