package com.islamichub.app.util

import android.content.Context
import com.islamichub.app.data.DataRepository

object Utils {
    fun getRepo(context: Context): DataRepository = DataRepository(context.applicationContext)
}
