package com.netmeds.netmedshealth.ui.MedicineDetailScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.netmeds.netmedshealth.R
import com.netmeds.netmedshealth.data.Medicine
import kotlinx.android.synthetic.main.activity_medicine_detail.*

class MedicineDetailActivity : AppCompatActivity(), DetailViewContract.View, View.OnClickListener {
    override fun showSuccessMessage(isSuccess: Boolean) {
        if (isSuccess)
            Toast.makeText(this@MedicineDetailActivity, "Medicine Added to Cart", Toast.LENGTH_SHORT).show()
    }

    var mMedicineDetail: Medicine? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_add_toCart -> {
                mActionListener!!.addToCart(mMedicineDetail!!.id!!)
            }

            R.id.iv_back -> {
                onBackPressed()
            }

        }
    }

    private var mActionListener: DetailViewContract.UserActionListener? = null


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMedicineDetail(medicine: Medicine) {
        mMedicineDetail = medicine
        tv_brand_name.text = "Name - " + medicine.brandname
        tv_company.text = "Company - " + medicine.company
        tv_category.text = "Category - " + medicine.category
        tv_cims_category.text = "CIMS Category - " + medicine.cimscategory
        tv_clasification.text = "Classification - " + medicine.clasification
        tv_created_date.text = "Created Date - " + medicine.createdate
        tv_discount.text = "Discount - " + medicine.discount
        tv_drug_nature.text = "Drug Nature - " + medicine.drugnature
        tv_drug_type.text = "Drug Type - " + medicine.drugtype
        tv_generic_dosage.text = "Generic Dosage - " + medicine.genericdosage
        tv_max_quantity.text = "Max Quantity - " + medicine.maxquantity
        tv_price.text = "Price - " + medicine.price
        tv_generic_name.text = "Generic Name - " + medicine.genericname
        tv_quantity_available.text = "Quantity Available - " + medicine.quantityavailable
        tv_cimssub_category.text = "CIMS SubCatgory - " + medicine.cimssubcategory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_detail)
        mActionListener = DetailPresenter(this)
        btn_add_toCart.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        (mActionListener as DetailPresenter).showMedicineDetail(intent.getStringExtra("id"))
    }
}
