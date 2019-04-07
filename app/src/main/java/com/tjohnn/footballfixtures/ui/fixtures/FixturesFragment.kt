package com.tjohnn.footballfixtures.ui.fixtures

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tjohnn.footballfixtures.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_leagues.view.*
import javax.inject.Inject

class FixturesFragment : DaggerFragment(){

    @Inject
    lateinit var mActivity: AppCompatActivity

    lateinit var tablesAdapter: TablesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {

    }

    companion object {

        val TAG = FixturesFragment::class.java.simpleName
        const val LEAGUE_ID_KEY = "LEAGUE_ID_KEY"
        const val LEAGUE_NAME_KEY = "LEAGUE_NAME_KEY"

        fun newInstance(leagueId : Long) : FixturesFragment {
            val bundle = Bundle()
            bundle.putLong(LEAGUE_ID_KEY, leagueId)
            val fragment = FixturesFragment()
            fragment.arguments = bundle
            return fragment
        }

    }
}
