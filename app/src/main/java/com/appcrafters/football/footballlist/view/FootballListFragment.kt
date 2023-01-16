package com.appcrafters.football.footballlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.football.R
import com.appcrafters.football.base.ICoordinator
import com.appcrafters.football.base.data.ApiServiceProvider
import com.appcrafters.football.base.functional.ViewModelFactoryUtil
import com.appcrafters.football.footballlist.viewmodel.FootballListViewModel
import com.appcrafters.football.footballlist.recycler.FootballRVAdapter
import com.appcrafters.football.base.data.FootballDataSource
import com.appcrafters.football.base.model.Football
import kotlinx.android.synthetic.main.fragment_football_list.*

class FootballListFragment : Fragment() {

    lateinit var viewModel: FootballListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            FootballListViewModel(FootballDataSource(ApiServiceProvider.footballApiService))
        }).get(FootballListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_football_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getMatches()
    }

    private fun setUpRecyclerView(football: Football) {
        footballListRV.adapter = FootballRVAdapter(football) { football ->
            (activity as ICoordinator).showDetailsFragment(football)
        }
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            footballListProgressBar.isVisible = state is FootballListViewState.Proccessing

            when (state) {
                is FootballListViewState.DataReceived -> { setUpRecyclerView(state.football) }
                is FootballListViewState.ErrorReceived -> showError(state.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}