package com.netmeds.netmedshealth.ui.homescreen

import com.netmeds.netmedshealth.data.Medicine
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults
import org.json.JSONArray
import org.json.JSONObject

class HomePresenter(private val viewContract: HomeViewContract.View) : HomeViewContract.UserActionListener {
    override fun searchForMedicine(searchKey: String) {
        val result: RealmResults<Medicine> =
            Realm.getDefaultInstance().where(Medicine::class.java).contains("brandname", searchKey, Case.INSENSITIVE)
                .findAll()
        viewContract.searchResult(result)
    }

    override fun getMedicineList() {
        viewContract.showProgress()
        val realm: Realm = Realm.getDefaultInstance()
        if (realm.where(Medicine::class.java).findAll().isEmpty()) {
            val jsonString = viewContract.getJSONFromAssets()
            loadMedicineList(jsonString)
        } else
            viewContract.showMedicineList(realm.where(Medicine::class.java).findAll())
    }

    override fun loadMedicineList(jsonString: String) {
        val realm: Realm = Realm.getDefaultInstance()
        val jsonObject = JSONObject(jsonString)
        val jsonArray: JSONArray = jsonObject.getJSONArray("medicines")
        val medicineList: ArrayList<Medicine> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val json_Object = jsonArray.getJSONObject(i)
            val medicine = Medicine()
            json_Object.let {
                medicine.id = it.get("id").toString()
                medicine.brandname = it["brandname"].toString()
                medicine.drugcode = it["drugcode"].toString()
                medicine.genericname = it["genericname"].toString()
                medicine.packsize = it["packsize"].toString()
                medicine.category = it["category"].toString()
                medicine.price = it["price"].toString()
                medicine.company = it["company"].toString()
                medicine.drugnature = it["drugnature"].toString()
                medicine.drugtype = it["drugtype"].toString()
                medicine.quantityavailable = it["quantityavailable"].toString()
                medicine.createdate = it["createdate"].toString()
                medicine.updatedate = it["updatedate"].toString()
                medicine.schedule = it["schedule"].toString()
                medicine.discount = it["discount"].toString()
                medicine.maxquantity = it["maxquantity"].toString()
                medicine.genericdosage = it["genericdosage"].toString()
                medicine.dosage = it["dosage"].toString()
                medicine.sellingprice = it["sellingprice"].toString()
                medicine.cimscategory = it["cimscategory"].toString()
                medicine.cimssubcategory = it["cimssubcategory"].toString()
                medicine.clasification = it["clasification"].toString()
                medicine.available = it["available"].toString()

            }
            medicineList.add(medicine)
            realm.executeTransaction { it.insert(medicine) }

        }
        viewContract.showMedicineList(realm.where(Medicine::class.java).findAll())
        viewContract.hideProgress()
    }

    override fun addToCart() {

    }

    override fun showMedicineDetail() {
    }
}