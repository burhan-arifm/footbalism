package burhanarifm.footbalism.tools.presenters

import burhanarifm.footbalism.tools.apis.ApiRepository
import burhanarifm.footbalism.tools.apis.FootballAPI
import burhanarifm.footbalism.tools.interfaces.MainView
import burhanarifm.footbalism.tools.data.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson
) {
    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(FootballAPI.getTeams(league)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}