package com.onthecrow.trainy.data.storage

import android.content.Context

class KeyValueStorage(context: Context) {

    companion object {
        private const val KEY_PREFERENCES = "todosha_shared_preferences"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
    }

    private val storage = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
}