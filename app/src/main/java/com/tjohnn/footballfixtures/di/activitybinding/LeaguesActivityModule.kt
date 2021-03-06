package com.tjohnn.footballfixtures.di.activitybinding

import android.support.v7.app.AppCompatActivity
import com.tjohnn.footballfixtures.di.annotations.ActivityScoped
import com.tjohnn.footballfixtures.di.annotations.FragmentScoped
import com.tjohnn.footballfixtures.ui.leagues.FixtureTableFragment
import com.tjohnn.footballfixtures.ui.leagues.LeaguesActivity
import com.tjohnn.footballfixtures.ui.leagues.LeaguesFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LeaguesActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector()
    abstract fun leaguesFragment(): LeaguesFragment


    @FragmentScoped
    @ContributesAndroidInjector()
    abstract fun fixtureTableFragment(): FixtureTableFragment


    @ActivityScoped
    @Binds
    abstract fun activity(leaguesActivity: LeaguesActivity): AppCompatActivity
}

@Module
class  LeaguesFragmentModule
