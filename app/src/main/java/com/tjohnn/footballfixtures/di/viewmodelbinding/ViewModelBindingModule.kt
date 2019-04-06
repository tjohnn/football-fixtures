package com.tjohnn.footballfixtures.di.viewmodelbinding

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tjohnn.footballfixtures.ui.leagues.LeaguesViewModel
import com.tjohnn.footballfixtures.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(LeaguesViewModel::class)
    abstract fun leaguesViewModel(leaguesViewModel: LeaguesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}