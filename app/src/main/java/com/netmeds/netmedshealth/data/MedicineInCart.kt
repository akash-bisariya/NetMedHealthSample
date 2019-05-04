package com.netmeds.netmedshealth.data

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.io.Serializable


open class MedicineInCart(
    @Required
    @PrimaryKey
    @Index
    var id: String = "", var cartCount: Int = 0): RealmObject(), Serializable {
}