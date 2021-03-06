package com.tjohnn.footballfixtures.ui.leagues

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.data.dto.League
import com.tjohnn.footballfixtures.ui.fixtures.FixturesActivity
import com.tjohnn.footballfixtures.ui.fixtures.FixturesFragment
import com.tjohnn.footballfixtures.utils.Utils
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_fixture_table.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_leagues.view.*
import javax.inject.Inject

class LeaguesFragment : DaggerFragment(), LeaguesAdapter.OnLeagueItemListener {


    @Inject
    lateinit var mActivity: AppCompatActivity
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: LeaguesViewModel

    private lateinit var leaguesAdapter: LeaguesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, factory).get(LeaguesViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_leagues, container, false)

        setupAdapter(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModel()
    }

    override fun onResume() {
        super.onResume()
        mActivity.title = getString(R.string.leagues)
    }

    private fun subscribeToViewModel() {
        mViewModel.getIsLoading().observe(this, Observer {

            view?.progressBar?.run {
                visibility = if (it?.getContentIfNotHandled() != null && it.peekContent() == true)
                    View.VISIBLE
                else
                    View.INVISIBLE
            }
        })

        mViewModel.getLeagues().observe(this, Observer {
            if(it == null || it.isEmpty()){
                mViewModel.loadLeagues()
            } else{
                val list = it
                leaguesAdapter.items = list as ArrayList<League>
                leaguesAdapter.notifyDataSetChanged()
                Log.d(TAG, it.toString())
            }

        })

        mViewModel.getSnackBarMessage().observe(this, Observer {
            if(it?.getContentIfNotHandled() != null){
                Snackbar.make(view!!, it.peekContent().toString(), Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun setupAdapter(view: View) {
        leaguesAdapter = LeaguesAdapter(this)
        view.leagueList?.run {
            Log.e(TAG, "I see you")
            Log.e(TAG, leaguesAdapter.toString())
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mActivity)
            adapter = leaguesAdapter
        }
    }

    override fun onLeagueItemClicked(league: League) {
        Utils.replaceFragmentToBackStack(mActivity.supportFragmentManager,
                FixtureTableFragment.newInstance(league.id, league.name), FixtureTableFragment.TAG, R.id.content_leagues)
    }

    companion object {

        val TAG = LeaguesFragment::class.java.simpleName

        fun newInstance(): LeaguesFragment {
            return LeaguesFragment()
        }

    }


}