package com.auto.autoads.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "create table favorit_ads ("
                    + "id integer primary key autoincrement,"
                    + "dataJson text" + ");"
        );
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    companion object {
        const val DB_NAME = "db_app_ads"
        @JvmStatic
        fun newInstance(context: Context) = DbHelper(context)
    }
}