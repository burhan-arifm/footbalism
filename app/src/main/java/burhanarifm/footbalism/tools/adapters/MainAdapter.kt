package burhanarifm.footbalism.tools.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import burhanarifm.footbalism.ankos.TeamUI
import burhanarifm.footbalism.tools.data.Team
import burhanarifm.footbalism.tools.holders.TeamHolder
import org.jetbrains.anko.AnkoContext

class MainAdapter (private val teams : List<Team>) : RecyclerView.Adapter<TeamHolder>() {
    override fun onCreateViewHolder(group: ViewGroup, p1: Int): TeamHolder {
        return TeamHolder(TeamUI().createView(AnkoContext.create(group.context, group)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.bind(teams[position])
    }
}