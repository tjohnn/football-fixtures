package com.tjohnn.footballfixtures.di.activitybinding

import android.support.v7.app.AppCompatActivity
import com.tjohnn.footballfixtures.di.annotations.ActivityScoped
import com.tjohnn.footballfixtures.di.annotations.FragmentScoped
import com.tjohnn.footballfixtures.ui.fixtures.FixturesActivity
import com.tjohnn.footballfixtures.ui.fixtures.FixturesFragment
import com.tjohnn.footballfixtures.ui.leagues.FixtureTableFragment
import com.tjohnn.footballfixtures.ui.leagues.LeaguesActivity
import com.tjohnn.footballfixtures.ui.leagues.LeaguesFragment
import com.tjohnn.footballfixtures.ui.table.TableActivity
import com.tjohnn.footballfixtures.ui.table.TableFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FixturesActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector()
    abstract fun fixturesFragment(): FixturesFragment



    @ActivityScoped
    @Binds
    abstract fun activity(tableActivity: FixturesActivity): AppCompatActivity
}

