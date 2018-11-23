package burhanarifm.footbalism.tools.interfaces

import burhanarifm.footbalism.tools.data.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}