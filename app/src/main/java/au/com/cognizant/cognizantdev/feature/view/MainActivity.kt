package au.com.cognizant.cognizantdev.feature.view

import android.app.Activity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toolbar
import au.com.cognizant.cognizantdev.R
import au.com.cognizant.cognizantdev.feature.adapter.AboutCanadaAdapter
import au.com.cognizant.cognizantdev.feature.model.AboutCanada
import au.com.cognizant.cognizantdev.feature.model.Fact
import au.com.cognizant.cognizantdev.network.ApiClient
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), MainContract.MainInteractable {

    companion object {
        private val ABOUT_CANADA_STATE_KEY = this.javaClass.`package`.name + ".canada"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.swipeRefreshLayout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val aboutCanadaAdapter = AboutCanadaAdapter()
    private lateinit var presenter: MainPresenter

    private lateinit var saveDataSetState: AboutCanada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val savedState = savedInstanceState?.get(ABOUT_CANADA_STATE_KEY) as AboutCanada?

        ButterKnife.bind(this)
        setActionBar(toolbar)
        configureRecyclerView()

        presenter = MainPresenter(this, savedState, ApiClient.getService())
        presenter.startPresenting()
        swipeRefreshLayout.setOnRefreshListener { presenter.refreshDataSet() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        saveDataSetState = AboutCanada(toolbar.title.toString(), aboutCanadaAdapter.factList)
        outState?.putParcelable(ABOUT_CANADA_STATE_KEY, saveDataSetState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoreState = savedInstanceState?.get(ABOUT_CANADA_STATE_KEY) as AboutCanada?
        restoreState?.let {
            toolbar.title = it.title
            aboutCanadaAdapter.updateDataset(it.facts)
        }
    }

    /**
     * sets the layout manager for the recycler view and assigns the instance of the adapter
     */
    private fun configureRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = aboutCanadaAdapter
        }
    }

    override fun setToolbar(title: String) {
        toolbar.title = title
    }

    override fun updateDataset(facts: MutableList<Fact>) {
        aboutCanadaAdapter.apply {
            updateDataset(facts)
            notifyDataSetChanged()
        }
    }
    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }
}
