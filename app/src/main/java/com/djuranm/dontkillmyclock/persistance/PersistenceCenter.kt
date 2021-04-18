package com.djuranm.dontkillmyclock.persistance

import android.content.Context
import com.djuranm.dontkillmyclock.R

class PersistenceCenter {

    companion object {

        fun getState(context: Context): Boolean {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
            return sharedPref.getBoolean(context.getString(R.string.state_key), false)
        }

        fun saveState(context: Context, shown: Boolean) {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putBoolean(context.getString(R.string.state_key), shown)
                apply()
            }
        }

    }

}