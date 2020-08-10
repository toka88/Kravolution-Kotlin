package com.gorantokovic.kravolution.activities.navigation

import com.gorantokovic.kravolution.activities.BaseFragment

abstract class BaseNavigationFragment: BaseFragment() {
    abstract fun getBackgroundDrawableId(): Int

    protected fun openDrawer() {
        (activity as? NavigationActivity)?.openDrawer()
    }
    protected fun closeDrawer() {
        (activity as? NavigationActivity)?.closeDrawer()
    }
}