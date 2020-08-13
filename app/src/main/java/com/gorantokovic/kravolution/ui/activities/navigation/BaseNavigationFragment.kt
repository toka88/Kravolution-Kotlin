package com.gorantokovic.kravolution.ui.activities.navigation

import com.gorantokovic.kravolution.ui.activities.BaseFragment

abstract class BaseNavigationFragment: BaseFragment() {
    abstract fun getBackgroundDrawableId(): Int

    protected fun openDrawer() {
        (activity as? NavigationActivity)?.openDrawer()
    }
    protected fun closeDrawer() {
        (activity as? NavigationActivity)?.closeDrawer()
    }
}