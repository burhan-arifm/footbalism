package burhanarifm.footbalism.ankos

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import burhanarifm.footbalism.R
import burhanarifm.footbalism.activities.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivityUI : AnkoComponent<MainActivity> {


    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui) {
            linearLayout {
                lparams (width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner = spinner() {
                    id = R.id.league_spinner

                }
                swipeRefresh = swipeRefreshLayout {
                    id = R.id.main_swipe
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    relativeLayout{
                        lparams (width = matchParent, height = wrapContent)

                        listTeam = recyclerView {
                            id = R.id.listRecycler
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(context)
                        }

                        progressBar = progressBar {
                            id = R.id.progress
                        }.lparams{
                            centerHorizontally()
                        }
                    }
                }
            }
        }
    }
}