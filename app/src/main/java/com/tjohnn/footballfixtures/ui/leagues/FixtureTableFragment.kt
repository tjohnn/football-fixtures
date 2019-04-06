package com.tjohnn.footballfixtures.ui.leagues

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.ui.HomeFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class FixtureTableFragment: DaggerFragment(){
    @Inject
    lateinit var mActivity: AppCompatActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val leageusButton = view.btn_leagues

        leageusButton.setOnClickListener {
            val intent = Intent(mActivity, LeaguesActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {

        val TAG = FixtureTableFragment::class.java.simpleName

        fun newInstance() : FixtureTableFragment {
            return FixtureTableFragment()
        }

    }
}