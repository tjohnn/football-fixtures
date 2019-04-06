package com.tjohnn.footballfixtures.ui.leagues

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tjohnn.footballfixtures.data.dto.League
import com.tjohnn.footballfixtures.data.repository.LeagueRepository
import com.tjohnn.footballfixtures.utils.EspressoIdlingResource
import com.tjohnn.footballfixtures.utils.EventWrapper
import com.tjohnn.footballfixtures.utils.Utils
import io.reactivex.disposables.CompositeDisposable
import ng.max.binger.utils.AppSchedulers
import javax.inject.Inject

class LeaguesViewModel @Inject constructor(
        app: Application,
        private var leagueRepository: LeagueRepository,
        private var appSchedulers: AppSchedulers
) : AndroidViewModel(app) {


    private var isLoading: MutableLiveData<EventWrapper<Boolean>> = MutableLiveData()
    private var leagues: LiveData<List<League>> = MutableLiveData()
    private var snackBarMessage: MutableLiveData<EventWrapper<String>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    init {
        // create a livedata to observe the list of leagues in case of any update
        leagues = leagueRepository.getLeagues()
    }


    fun getIsLoading(): LiveData<EventWrapper<Boolean>> {
        return isLoading
    }

    fun getSnackBarMessage(): LiveData<EventWrapper<String>> {
        return snackBarMessage
    }

    fun getLeagues(): LiveData<List<League>> {
        return leagues
    }

    /**
     * Loads leagues from the api
     */
    fun loadLeagues(){
        EspressoIdlingResource.increment()
        compositeDisposable.add(
                leagueRepository.loadLeagues()
                        .subscribeOn(appSchedulers.io())
                        .observeOn(appSchedulers.main())
                        .doOnSubscribe{isLoading.postValue(EventWrapper(false))}
                        .doAfterSuccess{
                            isLoading.postValue(EventWrapper(false))
                        }
                        .doOnError{isLoading.postValue(EventWrapper(false))}
                        .subscribe({
                            refreshLeaguesTable(it.data)
                            EspressoIdlingResource.decrement()
                        }, {
                            snackBarMessage.value = EventWrapper(Utils.processNetworkError(it))
                            EspressoIdlingResource.decrement()
                        }))

    }


    /**
     * Deletes all leagues that is currently saved and replace them with a new one
     */
    private fun refreshLeaguesTable(leagues: List<League>){
        compositeDisposable.add(
                leagueRepository.refreshLeagues(leagues)
                        .subscribeOn(appSchedulers.io())
                        .observeOn(appSchedulers.main())
                        .subscribe({

                        }, {
                            snackBarMessage.value = EventWrapper(Utils.processNetworkError(it))
                            EspressoIdlingResource.decrement()
                        }))
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}