package com.netmeds.netmedshealth.ui.homescreen

interface MedicineRepository {

    interface LoadMedicineListCallback {
        fun onMedicineListLoaded()
    }

    fun getMedicineList()
}