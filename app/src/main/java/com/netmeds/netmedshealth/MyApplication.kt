package com.netmeds.netmedshealth

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by akash
 * on 21/2/18.
 */
class MyApplication : Application() {
    companion object {
        lateinit var application_context: Context
        fun getAppContext(): Context {
            return application_context
        }
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        application_context = this
        val configuration: RealmConfiguration = RealmConfiguration.Builder()
            .name("medicine.realm")
            .schemaVersion(1)
//                .migration(RealmChangeMigration())
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(configuration)
    }
}