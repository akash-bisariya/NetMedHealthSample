package com.netmeds.netmedshealth.ui.cartscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.netmeds.netmedshealth.R
import com.netmeds.netmedshealth.data.Medicine
import com.netmeds.netmedshealth.data.MedicineInCart
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity(), CartViewContract.View, IOnRecycleItemClick, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        mActionListener = CartPresenter(this)
        (mActionListener as CartPresenter).getCartList()
        btn_checkout.setOnClickListener(this)
        iv_back.setOnClickListener(this)
    }


    override fun checkoutSuccess() {
        Toast.makeText(this@CartActivity, "You have successfully checkout the cart items", Toast.LENGTH_SHORT).show()
        mAdapter.notifyDataSetChanged()
        mCartList.clear()
        mMedicineList.clear()
        tv_cart_text.text = getString(R.string.txt_no_cart_items)
    }

    var mActionListener: CartViewContract.UserActionListener? = null
    var mMedicineList: ArrayList<Medicine?> = ArrayList()
    var mCartList: ArrayList<MedicineInCart> = ArrayList()
    lateinit var mAdapter: CartRecyclerAdapter

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_checkout -> {
                if (!mCartList.isEmpty()) {
                    if (mCartList[0].isValid)
                        mActionListener!!.saveCheckoutMedicine(mCartList)
                }
            }
            R.id.iv_back -> {
                onBackPressed()
            }
        }
    }

    override fun onRecycleItemClick(view: View?, position: Int, value: Int) {
        mActionListener!!.saveItemCount(mMedicineList.get(position)!!.id, value)
    }


    override fun showCartList(medicineList: ArrayList<Medicine?>, cartList: RealmResults<MedicineInCart>) {
        mMedicineList.clear()
        mCartList.addAll(cartList)
        mMedicineList.addAll(medicineList)
        if (!mMedicineList.isEmpty()) {
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            rv_cart.layoutManager = layoutManager
            mAdapter = CartRecyclerAdapter(this, mMedicineList, cartList, this)
            rv_cart.adapter = mAdapter
        } else {
            tv_cart_text.text = getString(R.string.txt_no_cart_items)
        }
    }

    override fun showSuccessMessage(isSuccess: Boolean) {
        if (isSuccess) {
            Toast.makeText(this@CartActivity, "Medicine is deleted from your cart", Toast.LENGTH_SHORT).show()
            mAdapter.notifyDataSetChanged()
        }
        if (!mCartList[0].isValid) {
            tv_cart_text.text = getString(R.string.txt_no_cart_items)
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }


}
