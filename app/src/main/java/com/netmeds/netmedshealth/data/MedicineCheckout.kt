package com.netmeds.netmedshealth.data

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.io.Serializable


open class MedicineCheckout(
    @Required
    @PrimaryKey
    @Index
    var id: String = ""
) : RealmObject(), Serializable {
}