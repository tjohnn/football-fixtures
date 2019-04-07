package com.tjohnn.footballfixtures.ui.table

import android.os.Bundle
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.utils.Utils
import dagger.android.support.DaggerAppCompatActivity

class TableActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        title = "${intent?.getStringExtra(TableFragment.LEAGUE_NAME_KEY)} ${getString(R.string.tables)}"



        if(savedInstanceState == null){

            if(intent?.hasExtra(TableFragment.LEAGUE_ID_KEY) == true){
                Utils.addFragment(supportFragmentManager,
                        TableFragment.newInstance(intent.getLongExtra(TableFragment.LEAGUE_ID_KEY, 0)),
                        TableFragment.TAG, R.id.content_table)
            }

        }
    }
}
