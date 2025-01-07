package com.mydailylift.app

import android.app.Application
import com.mydailylift.app.data.DailyLiftDatabase
import com.mydailylift.app.data.DatabaseModule


class DailyLiftApplication : Application() {

    val database: DailyLiftDatabase by lazy {
        DatabaseModule.getDatabase(this)
    }
}
