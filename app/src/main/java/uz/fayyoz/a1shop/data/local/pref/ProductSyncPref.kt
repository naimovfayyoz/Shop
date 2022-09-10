package uz.fayyoz.a1shop.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.productDataStore: DataStore<Preferences> by preferencesDataStore(name = "productSync_data_store")

class ProductSyncPref(context: Context) {

    private val appContext = context.applicationContext

    val lastSyncedTime: Flow<Long>
        get() = appContext.productDataStore.data.map {
            it[SYNCED_TIME] ?: 0
        }

    suspend fun saveLastSyncedTime(time: Long) {
        appContext.productDataStore.edit { preferences ->
            preferences[SYNCED_TIME] = time
        }
    }

    companion object {
        private val SYNCED_TIME = longPreferencesKey("key_last_synced_time")
    }

}