package com.idz.colman2026class2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val button = findViewById<Button>(R.id.main_add_student_button)

//        class MyOnClickListener : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                Log.v("TAG", "This button was clicked")
////                val intent = Intent(this, AddStudentActivity::class.java)
//                val intent = Intent(this@MainActivity, AddStudentActivity::class.java)
//                startActivity(intent)
//            }
//
//        }
//        val listener = MyOnClickListener()

        button.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
}