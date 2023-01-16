package com.appcrafters.football.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appcrafters.football.base.model.Match
import com.appcrafters.football.R
import com.appcrafters.football.footballlist.view.FootballListFragment
import com.appcrafters.football.footballdetails.view.FootballDetailsFragment
import com.appcrafters.football.base.model.Football

class MainActivity : AppCompatActivity(), ICoordinator {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showFragment(FootballListFragment(), true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showDetailsFragment(match: Match) {
        showFragment(FootballDetailsFragment.newInstance(match))
    }

}