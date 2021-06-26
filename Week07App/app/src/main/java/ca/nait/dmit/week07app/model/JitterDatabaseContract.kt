package ca.nait.dmit.week07app.model

import android.provider.BaseColumns

object JitterDatabaseContract {

    object JitterEntry : BaseColumns {
        const val TABLE_NAME = "Jitters"
        const val COLUMN_NAME_LOGINNAME = "LOGIN_NAME"
        const val COLUMN_NAME_MESSAGE = "DATA"
        const val COLUMN_NAME_DATE = "CREATED_DATE"
    }

}