package burhanarifm.footbalism.tools.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import burhanarifm.footbalism.tools.data.Team
import org.jetbrains.anko.find
import burhanarifm.footbalism.R.id.*
import com.bumptech.glide.Glide

class TeamHolder (view : View) : RecyclerView.ViewHolder(view) {

    private val teamBadge : ImageView = view.find(team_badge)
    private val teamName : TextView = view.find(team_name)

    fun bind(teams : Team) {
        Glide.with(itemView.context).load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName
    }
}
