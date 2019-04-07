package com.tjohnn.footballfixtures.ui.leagues

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.ui.HomeFragment
import com.tjohnn.footballfixtures.ui.fixtures.FixturesActivity
import com.tjohnn.footballfixtures.ui.fixtures.FixturesFragment
import com.tjohnn.footballfixtures.ui.table.TableActivity
import com.tjohnn.footballfixtures.ui.table.TableFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_fixture_table.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class FixtureTableFragment: DaggerFragment(){
    @Inject
    lateinit var mActivity: AppCompatActivity

    private var leagueId: Long? = 0
    private var leagueName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            leagueId = getLong(LEAGUE_ID_KEY, 0)
            leagueName = getString(LEAGUE_NAME_KEY, "")
        }
    }

    override fun onResume() {
        super.onResume()
        mActivity.title = leagueName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fixture_table, container, false)

        val fixturesButton = view.btn_fixtures
        val tableButton = view.btn_tables

        tableButton.setOnClickListener {
            val intent = Intent(mActivity, TableActivity::class.java)
            intent.putExtra(TableFragment.LEAGUE_ID_KEY, leagueId)
            intent.putExtra(FixturesFragment.LEAGUE_NAME_KEY, leagueName)
            startActivity(intent)
        }

        fixturesButton.setOnClickListener {
            val intent = Intent(mActivity, FixturesActivity::class.java)
            intent.putExtra(FixturesFragment.LEAGUE_ID_KEY, leagueId)
            intent.putExtra(FixturesFragment.LEAGUE_NAME_KEY, leagueName)
            startActivity(intent)
        }

        return view
    }

    companion object {

        val TAG = FixtureTableFragment::class.java.simpleName
        const val LEAGUE_ID_KEY = "LEAGUE_ID_KEY"
        const val LEAGUE_NAME_KEY = "LEAGUE_NAME_KEY"

        fun newInstance(leagueId : Long, name: String) : FixtureTableFragment {
            val bundle = Bundle()
            bundle.putLong(LEAGUE_ID_KEY, leagueId)
            bundle.putString(LEAGUE_NAME_KEY, name)
            val fragment = FixtureTableFragment()
            fragment.arguments = bundle
            return fragment
        }

    }
}