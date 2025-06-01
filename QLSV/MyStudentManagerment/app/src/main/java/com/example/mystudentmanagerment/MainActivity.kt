package com.example.mystudentmanagerment

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudentmanagerment.adapter.StudentAdapter
import com.example.mystudentmanagerment.data.DBHelper
import com.example.mystudentmanagerment.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var edtMaSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var edtLop: EditText
    private lateinit var btnThem: Button
    private lateinit var recyclerView: RecyclerView

    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: StudentAdapter
    private var studentList: ArrayList<Student> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        edtMaSV = findViewById(R.id.edtMaSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtLop = findViewById(R.id.edtLop)
        btnThem = findViewById(R.id.btnThem)
        recyclerView = findViewById(R.id.recyclerView)

        dbHelper = DBHelper(this)
        studentList = dbHelper.getAllStudents()

        adapter = StudentAdapter(studentList,
            onEdit = { student ->
                // Xử lý sửa ở đây (hiển thị form sửa, gọi updateStudent,...)
                Toast.makeText(this, "Sửa: ${student.name}", Toast.LENGTH_SHORT).show()
            },
            onDelete = { student ->
                dbHelper.deleteStudent(student.id)
                studentList.remove(student)
                adapter.notifyDataSetChanged()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnThem.setOnClickListener {
            val maSV = edtMaSV.text.toString().trim().toIntOrNull()
            val hoTen = edtHoTen.text.toString().trim()
            val lop = edtLop.text.toString().trim()

            if (maSV == null  || hoTen.isEmpty() || lop.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                val student = Student(maSV, hoTen, lop)
                if (dbHelper.addStudent(student)) {
                    Toast.makeText(this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show()
                    studentList.clear()
                    studentList.addAll(dbHelper.getAllStudents())
                    adapter.notifyDataSetChanged()
                    clearInputs()
                } else {
                    Toast.makeText(this, "Mã sinh viên đã tồn tại", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clearInputs() {
        edtMaSV.text.clear()
        edtHoTen.text.clear()
        edtLop.text.clear()
    }
}