package com.tjohnn.footballfixtures.ui.table

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.data.dto.TableItem
import com.tjohnn.footballfixtures.ui.fixtures.TablesAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tables.view.*
import javax.inject.Inject

class TableFragment : DaggerFragment(){

    @Inject
    lateinit var mActivity: AppCompatActivity
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var mViewModel: TableViewModel

    private var leagueId: Long? = 0

    lateinit var tablesAdapter: TablesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, factory).get(TableViewModel::class.java)

        arguments?.run {
            leagueId = getLong(LEAGUE_ID_KEY, 0)
        }

        mViewModel.loadLeagues(leagueId!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tables, container, false)
        subscribeToViewModel()
        setupAdapter(view)
        return view
    }


    private fun setupAdapter(view: View) {
        tablesAdapter = TablesAdapter()
        view.tables?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mActivity)
            adapter = tablesAdapter
        }
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

        mViewModel.getTables().observe(this, Observer {
            if(it == null) return@Observer
            tablesAdapter.updateItems(it.standings[0].table as ArrayList<TableItem>)
            view?.teamName?.text = it.competition.name
        })

        mViewModel.getSnackBarMessage().observe(this, Observer {
            if(it?.getContentIfNotHandled() != null){
                Snackbar.make(view!!, it.peekContent().toString(), Snackbar.LENGTH_LONG).show()
            }
        })
    }

    companion object {

        val TAG = TableFragment::class.java.simpleName
        const val LEAGUE_ID_KEY = "LEAGUE_ID_KEY"
        const val LEAGUE_NAME_KEY = "LEAGUE_NAME_KEY"

        fun newInstance(leagueId : Long) : TableFragment {
            val bundle = Bundle()
            bundle.putLong(LEAGUE_ID_KEY, leagueId)
            val fragment = TableFragment()
            fragment.arguments = bundle
            return fragment
        }

    }
}
