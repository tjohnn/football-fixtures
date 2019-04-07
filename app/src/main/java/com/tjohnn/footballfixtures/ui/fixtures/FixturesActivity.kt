package com.tjohnn.footballfixtures.ui.fixtures

import android.os.Bundle
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.utils.Utils
import dagger.android.support.DaggerAppCompatActivity

class FixturesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixtures)

        title = "${intent?.getStringExtra(FixturesFragment.LEAGUE_NAME_KEY)} ${getString(R.string.fixtures)}"

        if(savedInstanceState == null){

            if(intent?.hasExtra(FixturesFragment.LEAGUE_ID_KEY) == true){
                Utils.addFragment(supportFragmentManager,
                        FixturesFragment.newInstance(intent.getLongExtra(FixturesFragment.LEAGUE_ID_KEY, 0)),
                        FixturesFragment.TAG, R.id.content_fixtures)
            }

        }
    }
}
