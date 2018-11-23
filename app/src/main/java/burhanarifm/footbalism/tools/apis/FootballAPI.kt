package burhanarifm.footbalism.tools.apis

import android.net.Uri
import burhanarifm.footbalism.BuildConfig

object FootballAPI {
    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.TSDB_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l", league)
            .build()
            .toString()
    }
}