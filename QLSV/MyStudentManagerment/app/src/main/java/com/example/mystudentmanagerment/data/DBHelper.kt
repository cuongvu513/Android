package com.example.mystudentmanagerment.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mystudentmanagerment.model.Student


class DBHelper(context: Context) : SQLiteOpenHelper(context, "StrudentDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE Student (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                className TEXT
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Student")
        onCreate(db)
    }

    fun addStudent(student: Student): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", student.name)
            put("className", student.className)
        }
        val result = db.insert("Student", null, values)
        db.close()
        return result != -1L
    }

    fun getAllStudents(): ArrayList<Student> {
        val list = ArrayList<Student>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Student", null)

        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndexOrThrow("id")
                val nameIndex = cursor.getColumnIndexOrThrow("name")
                val classNameIndex = cursor.getColumnIndexOrThrow("className")

                val student = Student(
                    id = cursor.getInt(idIndex),
                    name = cursor.getString(nameIndex),
                    className = cursor.getString(classNameIndex)
                )
                list.add(student)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return list
    }

    fun updateStudent(student: Student): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", student.name)
            put("className", student.className)
        }
        val result = db.update("Student", values, "id=?", arrayOf(student.id.toString()))
        db.close()
        return result > 0
    }

    fun deleteStudent(id: Int): Boolean {
        val db = writableDatabase
        val result = db.delete("Student", "id=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }
}