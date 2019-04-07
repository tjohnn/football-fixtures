package com.tjohnn.footballfixtures.ui.fixtures

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tjohnn.footballfixtures.R
import com.tjohnn.footballfixtures.data.dto.TableItem
import kotlinx.android.synthetic.main.list_item_table.view.*

class TablesAdapter: RecyclerView.Adapter<TablesAdapter.TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): TableViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_table, parent, false)
        return TableViewHolder(view)
    }

    var items: ArrayList<TableItem> = arrayListOf()

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: TableViewHolder, pos: Int) {
        Log.d("onBindViewHolder", "$pos")
        holder.bindData(items[pos])
    }

    fun updateItems(items: ArrayList<TableItem>) {
        this.items = items
        Log.d("TablesAdapter", items.toString())
        notifyDataSetChanged()
    }


    inner class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindData(item: TableItem){
            itemView.teamName.text = item.team.name
            itemView.played.text = "${item.played}"
            itemView.goalDifference.text = "${item.goalDifference}"
            itemView.points.text = "${item.points}"
        }
    }


}