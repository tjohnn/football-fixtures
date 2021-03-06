package ng.max.binger.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSchedulers @Inject constructor() {

    fun io() = Schedulers.io()
    fun main() = AndroidSchedulers.mainThread()
    fun computation() = Schedulers.computation()
}