package com.appcrafters.football.footballdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appcrafters.football.R
import com.appcrafters.football.base.model.Match
import kotlinx.android.synthetic.main.fragment_football_details.*
import java.text.SimpleDateFormat

class FootballDetailsFragment : Fragment() {

    companion object {
        private var MATCH: Match = Match()

        fun newInstance(match: Match): FootballDetailsFragment {

            return FootballDetailsFragment().apply {
                arguments = Bundle().apply {
                    MATCH = match
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_football_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFromViewModel(MATCH)
    }

    private fun bindFromViewModel(match: Match) {
        setUpView(match)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(match: Match) {
        hostNameTxt.text = match.team_1.name
        score2Txt.text =
            " " + match.score.full_time.team_1 + " : " + match.score.full_time.team_2 + "\n(" + match.score.half_time.team_1 + " : " + match.score.half_time.team_2 + ")"
        challengerNameTxt.text = match.team_2.name

        hostInfoTxt.text = "\tCountry: " + match.team_1.country + "\n\tName: " + match.team_1.name
        challengerInfoTxt.text = "\tCountry: " + match.team_2.country + "\n\tName: " + match.team_2.name

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        scheduledTxt.text = simpleDateFormat.format(match.time.scheduled).replace("2610", "2022")
    }
}