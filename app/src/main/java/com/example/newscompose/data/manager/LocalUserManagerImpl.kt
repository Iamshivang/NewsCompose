package com.example.newscompose.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newscompose.domain.manager.LocalUserManager
import com.example.newscompose.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/*
 * Author: Shivang Yadav
 * Created: 6/4/25
 * Description: [Add description here]
 */

@Singleton
class LocalUserManagerImpl @Inject constructor(
    @ApplicationContext val context: Context
): LocalUserManager {


    override suspend fun saveAppEntry() {
        context.dataStore.edit {  settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

private object PreferencesKeys{

    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}