package com.netmeds.netmedshealth.ui.MedicineDetailScreen

import com.netmeds.netmedshealth.data.Medicine
import com.netmeds.netmedshealth.data.MedicineInCart
import io.realm.Realm

class DetailPresenter(private val viewContract: DetailViewContract.View) :
    DetailViewContract.UserActionListener {
    override fun addToCart(id: String) {
        if (!checkForPreviouslyAdded(id)) {
            Realm.getDefaultInstance().executeTransaction { it.insert(MedicineInCart(id, 1)) }
        } else {
            val med = Realm.getDefaultInstance().where(MedicineInCart::class.java).equalTo("id", id).findFirst()
            Realm.getDefaultInstance().executeTransaction { it.insertOrUpdate(MedicineInCart(id, ++(med!!.cartCount))) }

        }
        viewContract.showSuccessMessage(true)
    }

    private fun checkForPreviouslyAdded(id: String): Boolean {
        val medicine = Realm.getDefaultInstance().where(MedicineInCart::class.java).equalTo("id", id).findFirst()
        return medicine != null
    }

    override fun showMedicineDetail(id: String) {
        val medicine = Realm.getDefaultInstance().where(Medicine::class.java).equalTo("id", id).findFirst()
        viewContract.showMedicineDetail(medicine as Medicine)
    }

}