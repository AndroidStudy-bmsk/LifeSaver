package com.lifesaver.data

import android.content.Context
import android.content.SharedPreferences

object LifeSaverPreferences {
    private const val USER_NAME = "name"
    private const val BLOOD_TYPE = "bloodType"
    private const val EMERGENCY_CONTACT = "emergencyContact"
    private const val BIRTHDATE = "birthDate"
    private const val NOTICE = "notice"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserName() = preferences.getString(USER_NAME, "미정")
    fun getBirthdate() = preferences.getString(BIRTHDATE, "미정")
    fun getBloodType() = preferences.getString(BLOOD_TYPE, "미정")
    fun getEmergencyContact() = preferences.getString(EMERGENCY_CONTACT, "미정")
    fun getNotice() = preferences.getString(NOTICE, "")

    // commit() // commit 을 사용할 경우 사용자 동작을 막을 수 있다. UI 스레드가 아닌 다른 스레드에서 작업하는 것을 권장한다.

    fun putUserName(userName: String) {
        with(preferences.edit()) {
            putString(USER_NAME, userName)
            apply()
        }
    }

    fun putBloodType(bloodType: String) {
        with(preferences.edit()) {
            putString(BLOOD_TYPE, bloodType)
            apply()
        }
    }

    fun putEmergencyContact(emergencyContact: String) {
        with(preferences.edit()) {
            putString(EMERGENCY_CONTACT, emergencyContact)
            apply()
        }
    }

    fun putBirthdate(birthdate: String) {
        with(preferences.edit()) {
            putString(BIRTHDATE, birthdate)
            apply()
        }
    }

    fun putNotice(notice: String) {
        with(preferences.edit()) {
            putString(NOTICE, notice)
            apply()
        }
    }

    fun clear() {
        with(preferences.edit()) {
            clear()
            apply()
        }
    }
}