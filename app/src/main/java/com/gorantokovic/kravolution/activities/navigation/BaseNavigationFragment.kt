package com.gorantokovic.kravolution.activities.navigation

import com.gorantokovic.kravolution.activities.BaseFragment

abstract class BaseNavigationFragment: BaseFragment() {
    abstract fun getBackgroundDrawableId(): Int
}