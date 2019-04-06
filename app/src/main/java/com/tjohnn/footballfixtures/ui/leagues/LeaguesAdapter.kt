package com.tjohnn.footballfixtures.ui.leagues

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.data.dto.League

class LeaguesAdapter(val mListener: OnLeagueItemListener): RecyclerView.Adapter<LeaguesAdapter.LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): LeagueViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_fixture, parent, false)
        return LeagueViewHolder(view)
    }

    private var items: ArrayList<League> = arrayListOf()

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: LeagueViewHolder, pos: Int) {
        Log.d("onBindViewHolder", "$pos")
        holder.bindData(items[pos])
    }

    fun updateItems(items: ArrayList<League>) {
        this.items = items
        notifyDataSetChanged()
    }


    inner class LeagueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindData(league: League){
            val textView = itemView as TextView
            textView.text = league.name
            itemView.setOnClickListener{
                mListener.onLeagueItemClicked(league)
            }
        }
    }

    interface OnLeagueItemListener{
        fun onLeagueItemClicked(league: League)
    }
}