package com.tjohnn.footballfixtures.ui.leagues

import android.os.Bundle

import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.utils.Utils

import dagger.android.support.DaggerAppCompatActivity

class LeaguesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)

        title = getString(R.string.leagues)

        if(savedInstanceState == null){
            Utils.addFragment(supportFragmentManager, LeaguesFragment.newInstance(), LeaguesFragment.TAG, R.id.content_leagues)
        }

    }


}
