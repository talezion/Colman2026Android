package com.idz.colman2026class2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.colman2026class2.models.Model
import com.idz.colman2026class2.models.Student

class StudentsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: 1 - Set a ListView in layout
        // TODO: 2 - Create an adapter for the ListView
        // TODO: 3 - Connect the ListView with the adapter
        // TODO: 4 - Create a data source for the adapter (e.g., list of students)
        // TODO: 5 - Create a layout for each item in the ListView

        val students = Model.shared.students

        val listView: ListView = findViewById(R.id.students_list_activity_list_view)
        listView.adapter = StudentListAdapter(
            students = students
        )
    }

    class StudentListAdapter(
        private val students: List<Student>
    ): BaseAdapter() {
        override fun getCount(): Int = students.size

        override fun getItem(p0: Int): Any? {
            return null
        }

        override fun getItemId(p0: Int): Long = 0

        override fun getView(
            postion: Int,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            val inflator = LayoutInflater.from(parent?.context)

            var view = convertView
            if (convertView == null) {
                view = inflator.inflate(R.layout.student_row_layout, parent, false)
                val checkbox: CheckBox = view.findViewById(R.id.checkbox)
                checkbox.setOnClickListener { view ->
                    (view?.tag as? Int)?.let { tag ->
                        students[tag].isPresent = checkbox.isChecked
                    }
                }
            }

            val nameTextView: TextView = view.findViewById(R.id.name_text_view)
            val idTextView: TextView = view.findViewById(R.id.id_text_view)
            val checkbox: CheckBox = view.findViewById(R.id.checkbox)

            nameTextView.text = students[postion].name
            idTextView.text = students[postion].id
            checkbox.apply {
                isChecked = students[postion].isPresent
                tag = postion
            }

            return view
        }
    }
}