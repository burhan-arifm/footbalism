package burhanarifm.footbalism.activities

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import burhanarifm.footbalism.R
import burhanarifm.footbalism.ankos.MainActivityUI
import burhanarifm.footbalism.tools.interfaces.MainView
import burhanarifm.footbalism.tools.data.Team
import burhanarifm.footbalism.tools.adapters.MainAdapter
import burhanarifm.footbalism.tools.apis.ApiRepository
import burhanarifm.footbalism.tools.presenters.MainPresenter
import burhanarifm.footbalism.tools.*
import butterknife.BindView
import butterknife.ButterKnife
import com.google.gson.Gson
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class MainActivity : AppCompatActivity(), MainView {

    //    Variable Declarations
    private var teams : MutableList<Team> = mutableListOf()
    private lateinit var presenter : MainPresenter
    private lateinit var leagueName : String
    private lateinit var adapter: MainAdapter
    @BindView(R.id.listRecycler)
    lateinit var listTeam: RecyclerView
    @BindView(R.id.progress)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.league_spinner)
    lateinit var spinner: Spinner
    @BindView(R.id.main_swipe)
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        ButterKnife.bind(this)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = MainAdapter(teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
