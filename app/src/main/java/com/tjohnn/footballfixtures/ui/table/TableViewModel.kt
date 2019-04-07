package com.tjohnn.footballfixtures.ui.table

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.tjohnn.footballfixtures.data.dto.League
import com.tjohnn.footballfixtures.data.dto.TableItem
import com.tjohnn.footballfixtures.data.dto.Tables
import com.tjohnn.footballfixtures.data.repository.LeagueRepository
import com.tjohnn.footballfixtures.utils.EspressoIdlingResource
import com.tjohnn.footballfixtures.utils.EventWrapper
import com.tjohnn.footballfixtures.utils.Utils
import io.reactivex.disposables.CompositeDisposable
import ng.max.binger.utils.AppSchedulers
import javax.inject.Inject

class TableViewModel @Inject constructor(
        app: Application,
        private var leagueRepository: LeagueRepository,
        private var appSchedulers: AppSchedulers
) : AndroidViewModel(app) {

    val TAG = "TableViewModel"
    private var isLoading: MutableLiveData<EventWrapper<Boolean>> = MutableLiveData()
    private var tables: MutableLiveData<Tables> = MutableLiveData()
    private var snackBarMessage: MutableLiveData<EventWrapper<String>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getIsLoading(): LiveData<EventWrapper<Boolean>> {
        return isLoading
    }

    fun getSnackBarMessage(): LiveData<EventWrapper<String>> {
        return snackBarMessage
    }

    fun getTables(): LiveData<Tables> {
        return tables
    }

    /**
     * Loads leagues from the api
     */
    fun loadLeagues(leagueId: Long){
        compositeDisposable.add(
                leagueRepository.loadLeagueTable(leagueId)
                        .subscribeOn(appSchedulers.io())
                        .observeOn(appSchedulers.main())
                        .doOnSubscribe{isLoading.postValue(EventWrapper(true))}
                        .doAfterSuccess{
                            isLoading.postValue(EventWrapper(false))
                        }
                        .doOnError{isLoading.postValue(EventWrapper(false))}
                        .subscribe({
                            tables.value = it
                        }, {
                            snackBarMessage.value = EventWrapper(Utils.processNetworkError(it))
                        }))

    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}