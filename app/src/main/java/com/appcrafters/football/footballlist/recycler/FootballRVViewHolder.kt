package com.appcrafters.football.footballlist.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.appcrafters.football.base.model.Match
import kotlinx.android.synthetic.main.item_football.view.*

class FootballRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(match: Match, onItemClicked: (Match) -> Unit) {
        itemView.hostTxt.text = match.team_1.name
        itemView.challengerTxt.text = match.team_2.name
        itemView.scoreTxt.text = match.score.full_time.team_1.toString() + " : " + match.score.full_time.team_2.toString()

        itemView.setOnClickListener {
            onItemClicked.invoke(match)
        }
    }
}