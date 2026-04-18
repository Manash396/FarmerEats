package com.mk.farmereats.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.map

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    //  SAVE TOKEN
    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    // GET TOKEN
    val tokenFlow: Flow<String?> = dataStore.data.map { prefs ->
        prefs[TOKEN_KEY]
    }

    //  CLEAR TOKEN (logout)
    suspend fun clearToken() {
        dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }
}