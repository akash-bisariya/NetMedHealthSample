package com.netmeds.netmedshealth.ui.homescreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.netmeds.netmedshealth.R
import com.netmeds.netmedshealth.data.Medicine
import kotlinx.android.synthetic.main.row_item_child_medicinelist.view.*
import kotlinx.android.synthetic.main.row_item_medicinelist.view.*


class MedicineRecyclerAdapter(val view: HomeViewContract.View, private val medicineList: ArrayList<Medicine>) :
    BaseExpandableListAdapter() {
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertViewNew: View? = null
        val childViewHolder: MedicineChildViewHolder

        if (convertView == null) {
            convertViewNew = LayoutInflater.from(view as Context).inflate(R.layout.row_item_child_medicinelist, null)
            childViewHolder = MedicineChildViewHolder(convertViewNew)
            childViewHolder.tvCategory = convertViewNew!!.findViewById(R.id.tvCategory)
            childViewHolder.tvDrugType = convertViewNew.findViewById(R.id.tvDrugType)
            childViewHolder.tvPrice = convertViewNew.findViewById(R.id.tvPrice)
            convertViewNew.tag = childViewHolder
        } else {
            childViewHolder = convertView.tag as MedicineChildViewHolder
            convertViewNew = convertView
        }
        childViewHolder.tvCategory.text = "Category :- ${medicineList[groupPosition]!!.category}"
        childViewHolder.tvDrugType.text = "Drug Type :- ${medicineList[groupPosition]!!.drugtype}"
        childViewHolder.tvPrice.text = "Price :- ${medicineList[groupPosition]!!.price}"

        return convertViewNew

    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true;
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Medicine? {
        return medicineList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String? {
        return medicineList.get(groupPosition)!!.brandname
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {

        var convertViewNew: View? = null
        val childViewHolder: MedicineParentViewHolder

        if (convertView == null) {
            convertViewNew = LayoutInflater.from(view as Context).inflate(R.layout.row_item_medicinelist, null)
            childViewHolder = MedicineParentViewHolder(convertViewNew)
            childViewHolder.tvCompany = convertViewNew!!.findViewById(R.id.tvCompanyName)
            childViewHolder.tvMedicineName = convertViewNew.findViewById(R.id.tvMedicineBrandName)
            childViewHolder.tvGenericName = convertViewNew.findViewById(R.id.tvGenericName)
            convertViewNew.tag = childViewHolder
        } else {
            childViewHolder = convertView.tag as MedicineParentViewHolder
            convertViewNew = convertView
        }
        childViewHolder.tvCompany.text = "Company :- ${medicineList[groupPosition]!!.company}"
        childViewHolder.tvMedicineName.text = "Medicine :- ${medicineList[groupPosition]!!.brandname}"
        childViewHolder.tvGenericName.text = "Generic Name :- ${medicineList[groupPosition]!!.genericname}"

        return convertViewNew
    }

    override fun getGroupCount(): Int {
        return medicineList.size
    }

    class MedicineParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMedicineName = itemView.tvMedicineBrandName!!
        var tvCompany = itemView.tvCompanyName!!
        var tvGenericName = itemView.tvGenericName!!

    }

    class MedicineChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDrugType = itemView.tvDrugType!!
        var tvCategory = itemView.tvCategory!!
        var tvPrice = itemView.tvPrice!!
    }
}