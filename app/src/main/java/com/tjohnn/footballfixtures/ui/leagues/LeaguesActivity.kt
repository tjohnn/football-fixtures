package com.tjohnn.footballfixtures.ui.leagues

import android.os.Bundle
import android.view.MenuItem

import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.utils.Utils

import dagger.android.support.DaggerAppCompatActivity

class LeaguesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)

        title = getString(R.string.leagues)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(savedInstanceState == null){
            Utils.addFragment(supportFragmentManager, LeaguesFragment.newInstance(), LeaguesFragment.TAG, R.id.content_leagues)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}
