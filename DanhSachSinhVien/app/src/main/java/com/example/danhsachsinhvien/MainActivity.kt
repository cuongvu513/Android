package com.example.danhsachsinhvien

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtMSSV: EditText
    private lateinit var btnAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtMSSV = findViewById(R.id.edtMSSV)
        btnAdd = findViewById(R.id.btnAdd)
        recyclerView = findViewById(R.id.recyclerView)

        studentAdapter = StudentAdapter(studentList) { position ->
            studentList.removeAt(position)
            studentAdapter.notifyItemRemoved(position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        btnAdd.setOnClickListener {
            val name = edtName.text.toString()
            val mssv = edtMSSV.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                studentList.add(0, Student(name, mssv)) // Chèn vào đầu danh sách
                studentAdapter.notifyItemInserted(0)
                edtName.text.clear()
                edtMSSV.text.clear()
            }
        }
    }
}
