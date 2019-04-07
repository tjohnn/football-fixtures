package com.tjohnn.footballfixtures.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object Utils {

    fun processNetworkError(throwable: Throwable): String? {
        return when (throwable) {
            is HttpException -> "Server error!! Please try later."
            is SocketTimeoutException -> "Network timeout! Ensure a better connection and retry."
            is IOException -> "Network error. Ensure you are connected to internet"
            else -> throwable.message
        }
    }

    fun addFragmentToBackStack(fragmentManager: FragmentManager, fragment: Fragment,
                               tag: String, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment, tag)
                .addToBackStack(tag).commit()
    }

    fun replaceFragmentToBackStack(fragmentManager: FragmentManager, fragment: Fragment,
                               tag: String, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, tag)
                .addToBackStack(tag).commit()
    }

    fun addFragment(fragmentManager: FragmentManager, fragment: Fragment,
                    tag: String, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment, tag)
                .commit()
    }

}
