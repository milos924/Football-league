package com.appcrafters.football.base

import com.appcrafters.football.base.model.Match

interface ICoordinator {
    fun showDetailsFragment(match: Match)
}