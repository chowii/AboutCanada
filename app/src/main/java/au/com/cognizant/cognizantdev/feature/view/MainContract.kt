package au.com.cognizant.cognizantdev.feature.view

import au.com.cognizant.cognizantdev.feature.model.Fact

interface MainContract {

    interface MainPresentable {

        /**
         * Triggered by view to start presentation
         */
        fun startPresenting()

        /**
         * Triggered by view when view is refreshed
         */
        fun refreshDataSet()

    }

    interface MainInteractable {

        /**
         *  Presenter triggers to view to show loading indicator
         */
        fun showLoading()

        /**
         * Presenter triggers to set title for toolbar
         */
        fun setToolbar(title: String)

        /**
         *  Presenter triggers to recycler view to update dataset and notify data set change
         */
        fun updateDataset(facts: MutableList<Fact>)

        /**
         *  Presenter triggers to view to hide loading indicator
         */
        fun hideLoading()

    }
}

