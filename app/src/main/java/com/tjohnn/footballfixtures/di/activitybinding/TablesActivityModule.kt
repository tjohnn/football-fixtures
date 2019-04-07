package com.tjohnn.footballfixtures.di.activitybinding

import android.support.v7.app.AppCompatActivity
import com.tjohnn.footballfixtures.di.annotations.ActivityScoped
import com.tjohnn.footballfixtures.di.annotations.FragmentScoped
import com.tjohnn.footballfixtures.ui.leagues.FixtureTableFragment
import com.tjohnn.footballfixtures.ui.leagues.LeaguesActivity
import com.tjohnn.footballfixtures.ui.leagues.LeaguesFragment
import com.tjohnn.footballfixtures.ui.table.TableActivity
import com.tjohnn.footballfixtures.ui.table.TableFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TablesActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector()
    abstract fun tablesFragment(): TableFragment



    @ActivityScoped
    @Binds
    abstract fun activity(tableActivity: TableActivity): AppCompatActivity
}

