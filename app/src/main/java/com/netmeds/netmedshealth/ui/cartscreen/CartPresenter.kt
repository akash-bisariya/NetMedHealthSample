package com.netmeds.netmedshealth.ui.cartscreen

import com.netmeds.netmedshealth.data.Medicine
import com.netmeds.netmedshealth.data.MedicineInCart
import io.realm.Realm


class CartPresenter(private val viewContract: CartViewContract.View) : CartViewContract.UserActionListener {
    var mCartMedicineList: ArrayList<Medicine?> = ArrayList()
    override fun saveCheckoutMedicine(mCartList: ArrayList<MedicineInCart>) {
        var success: Boolean = false
        Realm.getDefaultInstance().executeTransaction {
            it.insertOrUpdate(mCartList)
            success = Realm.getDefaultInstance().where(MedicineInCart::class.java).findAll().deleteAllFromRealm()
        }
        if (success)
            viewContract.checkoutSuccess()
    }


    override fun saveItemCount(id: String?, cartCount: Int) {
        if (cartCount != 0)
            Realm.getDefaultInstance().executeTransaction { it.insertOrUpdate(MedicineInCart(id as String, cartCount)) }
        else {
            Realm.getDefaultInstance().executeTransaction {
                val rows = Realm.getDefaultInstance().where(MedicineInCart::class.java).equalTo("id", id).findAll()
                rows.deleteAllFromRealm()
                viewContract.showSuccessMessage(true)
            }
        }
    }

    override fun getCartList() {
        val list = Realm.getDefaultInstance().where(MedicineInCart::class.java).findAll()
        for (item in 0 until list.size) {
            mCartMedicineList.add(
                Realm.getDefaultInstance().where(Medicine::class.java).equalTo(
                    "id",
                    list[item]!!.id
                ).findFirst()
            )
        }
        viewContract.showCartList(mCartMedicineList, list)
    }


}