package com.netmeds.netmedshealth.ui.cartscreen

import com.netmeds.netmedshealth.data.Medicine
import com.netmeds.netmedshealth.data.MedicineInCart
import io.realm.RealmResults

public interface CartViewContract {

    interface View {

        fun showCartList(medicineList: ArrayList<Medicine?>, cartList: RealmResults<MedicineInCart>)

        fun showSuccessMessage(isSuccess: Boolean)

        fun showProgress()

        fun hideProgress()

        fun checkoutSuccess()

    }

    interface UserActionListener {

        fun getCartList()

        fun saveItemCount(id: String?, cartCount: Int)

        fun saveCheckoutMedicine(mCartList: ArrayList<MedicineInCart>)
    }
}
