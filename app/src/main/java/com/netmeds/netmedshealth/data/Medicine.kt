package com.netmeds.netmedshealth.data

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.io.Serializable


open class Medicine : RealmObject(), Serializable {

    @Required
    @PrimaryKey
    @Index
    var id: String? = null

    var drugcode: String? = null

    var brandname: String? = null

    var genericname: String? = null

    var packsize: String? = null

    var price: String? = null

    var company: String? = null

    var drugnature: String? = null

    var drugtype: String? = null

    var quantityavailable: String? = null

    var available: String? = null

    var createdate: String? = null

    var updatedate: String? = null

    var schedule: String? = null

    var discount: String? = null

    var maxquantity: String? = null

    var genericdosage: String? = null

    var dosage: String? = null

    var sellingprice: String? = null

    var cimscategory: String? = null

    var cimssubcategory: String? = null

    var clasification: String? = null

    var category: String? = null

}