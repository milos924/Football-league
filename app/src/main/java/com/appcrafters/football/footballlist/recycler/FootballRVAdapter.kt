package com.appcrafters.football.footballlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.appcrafters.football.R
import com.appcrafters.football.base.model.Football
import com.appcrafters.football.base.model.Match

class FootballRVAdapter(private val football: Football, private val onItemClicked: (Match) -> Unit) :
    Adapter<FootballRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FootballRVViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_football, parent, false)
    )

    override fun onBindViewHolder(holder: FootballRVViewHolder, position: Int) {
        holder.bind(football.data[position], onItemClicked)
    }

    override fun getItemCount() = football.data.size
}