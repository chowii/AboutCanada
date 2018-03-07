package au.com.cognizant.cognizantdev.feature.view

import android.util.Log
import au.com.cognizant.cognizantdev.feature.model.AboutCanada
import au.com.cognizant.cognizantdev.network.CognizantService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainContract.MainInteractable,
                    private val savedState: AboutCanada?,
                    private val apiService: CognizantService
) : MainContract.MainPresentable {

    override fun startPresenting() {
        if (savedState == null) {
            getData()
        }
    }

    /**
     * Triggers view to show loading dialog, gets data from api service, and hides loading indicator
     */
    private fun getData() {
        view.showLoading()
        apiService.getDataset()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.hideLoading()
                            view.setToolbar(it.title)
                            val filtered = it.facts.filter { !it.isNull }.toMutableList()
                            view.updateDataset(filtered)
                        },
                        {
                            view.hideLoading()
                            Log.d(javaClass.name, "onCreate(): ${it.message}")
                        }
                )
    }


    override fun refreshDataSet() {
        getData()
    }
}