package com.netmeds.netmedshealth.ui.cartscreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.netmeds.netmedshealth.R
import com.netmeds.netmedshealth.data.Medicine
import com.netmeds.netmedshealth.data.MedicineInCart
import io.realm.RealmResults
import kotlinx.android.synthetic.main.row_item_cart.view.*

class CartRecyclerAdapter(
    private var viewCartContract: CartViewContract.View,
    var medicineList: ArrayList<Medicine?>,
    var cartList: RealmResults<MedicineInCart>,
    private val onItemClick: IOnRecycleItemClick
) : RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewCartContract as Context).inflate(R.layout.row_item_cart, parent, false)
        return ViewHolder(view, onItemClick)

    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewHolderBind(medicineList[position], cartList[position], onItemClick)
    }

    class ViewHolder(itemView: View, private var onItemClick: IOnRecycleItemClick) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.tvMedicineBrandName!!
        private val tvCompany = itemView.tvCompanyName!!
        private val tvPrice = itemView.tvPrice!!
        private val itemCount = itemView.item_count!!

        fun viewHolderBind(medicine: Medicine?, medicineInCart: MedicineInCart?, listener: IOnRecycleItemClick) {
            onItemClick = listener
            itemCount.setOnValueChangeListener(object : ElegantNumberButton.OnValueChangeListener {
                override fun onValueChange(view: ElegantNumberButton?, oldValue: Int, newValue: Int) {
                    onItemClick.onRecycleItemClick(view, adapterPosition, newValue)
                }

            })
            tvCompany.text = medicine!!.company
            tvName.text = medicine.brandname
            tvPrice.text = medicine.price
            itemCount.number = medicineInCart!!.cartCount.toString()
        }
    }


}