package com.tutorial.c59

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val studentSql = """
           create table tb_member(
           _id integer primary key autoincrement,
           name not null,
           email, 
           phone)
        """
        db?.execSQL(studentSql)
        db?.execSQL("insert into tb_member (name, email, phone) " +
                "values ('haeun', 'jxlhe46@gmail.com', '01012345678')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table tb_member")
        onCreate(db)
    }
}