//package com.example.sinhvien
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ListView
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var inputHoten: EditText
//    private lateinit var inputMSSV: EditText
//    private lateinit var buttonAdd: Button
//    private lateinit var listView: ListView
//    private lateinit var studentAdapter: StudentAdapter
//    private val studentList = mutableListOf<Student>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        inputHoten = findViewById(R.id.inputHoten)
//        inputMSSV = findViewById(R.id.inputMSSV)
//        buttonAdd = findViewById<Button>(R.id.buttonAdd)
//        listView = findViewById<ListView>(R.id.list_students)
//
//        inputHoten.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus && inputHoten.text.toString() == "Họ và tên") {
//                inputHoten.setText("")
//            }
//        }
//        inputMSSV.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus && inputMSSV.text.toString() == "MSSV") {
//                inputMSSV.setText("")
//            }
//        }
//
//        studentAdapter = StudentAdapter(this, studentList)
//        listView.adapter = studentAdapter
//
//        buttonAdd.setOnClickListener {
//            val name = inputHoten.text.toString()
//            val mssv = inputMSSV.text.toString()
//
//            if (name.isNotEmpty() && mssv.isNotEmpty()) {
//                studentList.add(Student(name, mssv))
//                studentAdapter.notifyDataSetChanged()
//                inputHoten.text.clear()
//                inputMSSV.text.clear()
//            }
//        }
//
//    }
//}

package com.example.sinhvien

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val inputHoten = findViewById<EditText>(R.id.inputHoten)
//        val inputMSSV = findViewById<EditText>(R.id.inputMSSV)
        val edtName = findViewById<EditText>(R.id.inputHoten)
        val edtMSSV = findViewById<EditText>(R.id.inputMSSV)
        val btnAdd = findViewById<Button>(R.id.buttonAdd)
        val recyclerView = findViewById<RecyclerView>(R.id.list_students)

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        edtName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && edtName.text.toString() == "Họ và tên") {
                edtName.setText("")
            }
        }
        edtMSSV.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && edtMSSV.text.toString() == "MSSV") {
                edtMSSV.setText("")
            }
        }

        btnAdd.setOnClickListener {
            val name = edtName.text.toString().trim()
            val mssv = edtMSSV.text.toString().trim()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                studentList.add(Student(name, mssv))
                studentAdapter.notifyItemInserted(studentList.size - 1)
                edtName.text.clear()
                edtMSSV.text.clear()
            }
        }
    }
}
