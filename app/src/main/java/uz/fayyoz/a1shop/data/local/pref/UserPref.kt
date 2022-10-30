package uz.fayyoz.a1shop.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.fayyoz.a1shop.model.User

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data_store")

class UserPref(context: Context) {

    private val appContext = context.applicationContext

    val userName: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[USER_NAME]
        }

    suspend fun saveUserName(userName: String) {
        appContext.dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
        }
    }

    val password: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[PASSWORD]
        }

    suspend fun savePassword(password: String) {
        appContext.dataStore.edit { preferences ->
            preferences[PASSWORD] = password
        }
    }

    val accessToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    suspend fun saveAccessTokens(accessToken: String) {
        appContext.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            
        }
    }

    suspend fun clearToken() {
        appContext.dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN)
        }
    }

    suspend fun logOut() {
        appContext.dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN)
            preferences.remove(USER_NAME)
            preferences.remove(PASSWORD)
        }
    }


    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        private val USER_NAME = stringPreferencesKey("key_user_name")
        private val PASSWORD = stringPreferencesKey("key_password")
    }

}
