package burhanarifm.footbalism.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import burhanarifm.footbalism.R
import burhanarifm.footbalism.ankos.MainUI
import burhanarifm.footbalism.tools.interfaces.MainView
import burhanarifm.footbalism.tools.data.Team
import burhanarifm.footbalism.tools.adapters.MainAdapter
import burhanarifm.footbalism.tools.apis.ApiRepository
import burhanarifm.footbalism.tools.presenters.MainPresenter
import burhanarifm.footbalism.tools.*
import com.google.gson.Gson
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(), MainView {

    //    Variable Declarations
    private var teams : MutableList<Team> = mutableListOf()
    private lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUI().setContentView(this)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        MainUI().spinner.adapter = spinnerAdapter

        MainUI().listTeam.adapter = MainAdapter(teams)

        presenter = MainPresenter(this, ApiRepository(), Gson())

        MainUI().spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getTeamList(MainUI().spinner.selectedItem.toString())
            }
        }
    }

    override fun showLoading() {
        MainUI().progressBar.visible()
    }

    override fun hideLoading() {
        MainUI().progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        MainUI().swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        MainAdapter(teams).notifyDataSetChanged()
    }
}
