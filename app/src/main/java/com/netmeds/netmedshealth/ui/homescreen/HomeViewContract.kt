package com.netmeds.netmedshealth.ui.homescreen

import com.netmeds.netmedshealth.data.Medicine
import io.realm.RealmResults

public interface HomeViewContract {

    interface View {

        fun showMedicineList(medicineList: RealmResults<Medicine>)

        fun showErrorMessage()

        fun showProgress()

        fun hideProgress()

        fun getJSONFromAssets(): String

        fun searchResult(medicineList: RealmResults<Medicine>)

    }

    interface UserActionListener {

        fun getMedicineList()

        fun loadMedicineList(jsonString: String)

        fun addToCart()

        fun showMedicineDetail()

        fun searchForMedicine(searchKey: String)
    }
}
