package com.tjohnn.footballfixtures.ui

import android.os.Bundle
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.utils.Utils
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.home)

        if(savedInstanceState == null){
            Utils.addFragment(supportFragmentManager, HomeFragment.newInstance(), HomeFragment.TAG, R.id.content_main)
        }
    }



}
