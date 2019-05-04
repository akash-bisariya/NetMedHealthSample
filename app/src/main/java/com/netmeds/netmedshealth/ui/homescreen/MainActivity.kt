package com.netmeds.netmedshealth.ui.homescreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ExpandableListView
import com.netmeds.netmedshealth.R
import com.netmeds.netmedshealth.data.Medicine
import com.netmeds.netmedshealth.ui.MedicineDetailScreen.MedicineDetailActivity
import com.netmeds.netmedshealth.ui.cartscreen.CartActivity
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeViewContract.View, View.OnClickListener {
    private var mAdapter: MedicineRecyclerAdapter? = null
    private var mMedicineList: ArrayList<Medicine>? = ArrayList()
    private var mActionListener: HomeViewContract.UserActionListener? = null


    override fun searchResult(medicineList: RealmResults<Medicine>) {
        if (mMedicineList != null)
            mMedicineList!!.clear()
        mMedicineList!!.addAll(medicineList)
        hideProgress()
        mAdapter!!.notifyDataSetChanged()

    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.iv_search -> {
                tv_app_name.visibility = View.GONE
                et_search.visibility = View.VISIBLE
                if (!et_search.text.isEmpty())
                    mActionListener!!.searchForMedicine(et_search.text.toString())

            }
            R.id.iv_cart -> {
                val intent = Intent(this@MainActivity, CartActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv_search.setOnClickListener(this)
        toolbar.setOnClickListener(this)
        iv_cart.setOnClickListener(this)
        mActionListener = HomePresenter(this)
        mActionListener!!.getMedicineList()
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isEmpty()) {
                    (mActionListener as HomePresenter).getMedicineList()
                    if (et_search.visibility == View.VISIBLE) {
                        tv_app_name.visibility = View.VISIBLE
                        et_search.visibility = View.GONE
                        hideSoftKeyboard()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        rv_medicine.setOnChildClickListener(object : ExpandableListView.OnChildClickListener {
            override fun onChildClick(
                parent: ExpandableListView?,
                v: View?,
                groupPosition: Int,
                childPosition: Int,
                id: Long
            ): Boolean {
                val id = mMedicineList!!.get(groupPosition).id
                val intent = Intent(this@MainActivity, MedicineDetailActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                return true
            }

        })

    }


    override fun showMedicineList(medicineList: RealmResults<Medicine>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mMedicineList!!.clear()
        mMedicineList!!.addAll(medicineList)
        mAdapter = MedicineRecyclerAdapter(this, mMedicineList as ArrayList<Medicine>)
        rv_medicine.setAdapter(mAdapter)
        hideProgress()
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    override fun showErrorMessage() {

    }

    override fun showProgress() {
        pb_progress.visibility = View.VISIBLE
        rv_medicine.visibility = View.GONE
    }

    override fun hideProgress() {
        pb_progress.visibility = View.GONE
        rv_medicine.visibility = View.VISIBLE
    }


    override fun getJSONFromAssets(): String {

        val inputStream = assets.open("medicines.json")

        val buffer = ByteArray(inputStream.available())

        inputStream.read(buffer)

        inputStream.close()

        return String(buffer)


    }
}
