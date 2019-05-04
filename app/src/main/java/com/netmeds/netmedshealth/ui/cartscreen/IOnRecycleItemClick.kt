package com.netmeds.netmedshealth.ui.cartscreen

import android.view.View

interface IOnRecycleItemClick {
    fun onRecycleItemClick(view: View?, position: Int, value: Int)
}