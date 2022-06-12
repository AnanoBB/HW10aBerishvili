package com.example.hw10aberishvili.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import kotlin.Int


class SQLHandler(context: Context): SQLiteOpenHelper(context,dbName,null,dbVersion) {
    companion object {
        private const val dbVersion = 1
        private const val dbName = "sqlite_database"
        private const val tName = "action_records"
        private const val pKeyId = "id"
        private const val stateKey = "state"
        private const val actionTimeKey = "action_time"
        private const val actionTypeKey = "action_type"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createEventRecordsTable = ("CREATE TABLE " + tName + "("
                + pKeyId + " INTEGER PRIMARY KEY," + stateKey + " TEXT,"
                + actionTimeKey + " TEXT," +  actionTypeKey + " TEXT" +")")
        db?.execSQL(createEventRecordsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tName")
        onCreate(db)
    }


    @SuppressLint("Range", "Recycle")
    fun getAllActionsRecords():List<ActionsData> {
        val recordList: ArrayList<ActionsData> = ArrayList()
        val selectQuery = "SELECT  * FROM $tName"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var state: String
        var actionTime: String
        var actionType: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                state = cursor.getString(cursor.getColumnIndex("state"))
                actionTime = cursor.getString(cursor.getColumnIndex("action_time"))
                actionType = cursor.getString(cursor.getColumnIndex("action_type"))
                val evt = ActionsData(
                    id = id,
                    actionState = state.toInt(),
                    actionTime = actionTime,
                    actionType = actionType
                )
                recordList.add(evt)
            } while (cursor.moveToNext())
        }
        return recordList
    }


    fun addActions(action: ActionsData):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(pKeyId, action.id)
        contentValues.put(stateKey, action.actionState)
        contentValues.put(actionTimeKey,action.actionTime )
        contentValues.put(actionTypeKey,action.actionType )

        val success = db.insert(tName, null, contentValues)

        db.close()
        return success
    }

}