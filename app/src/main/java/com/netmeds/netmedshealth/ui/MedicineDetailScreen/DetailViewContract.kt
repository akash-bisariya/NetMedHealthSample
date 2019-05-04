package com.netmeds.netmedshealth.ui.MedicineDetailScreen

import com.netmeds.netmedshealth.data.Medicine

public interface DetailViewContract {

    interface View {

        fun showMedicineDetail(medicine: Medicine)

        fun showSuccessMessage(isSuccess: Boolean)

        fun showProgress()

        fun hideProgress()

    }

    interface UserActionListener {

        fun addToCart(id: String)

        fun showMedicineDetail(id: String)

    }
}
