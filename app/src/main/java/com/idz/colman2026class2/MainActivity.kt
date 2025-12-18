package com.idz.colman2026class2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.idz.colman2026class2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var fragmentOne: Fragment? = null
    private var fragmentTwo: Fragment? = null
    private var fragmentThree: Fragment? = null
    private var fragmentFour: Fragment? = null
    private var displayedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fragmentOne = StudentsListFragment.newInstance()
        fragmentTwo = BlueFragment.newInstance("Two")
        fragmentThree = BlueFragment.newInstance("Three")
        fragmentFour = BlueFragment.newInstance("Four")

        binding?.buttonOne?.setOnClickListener(::onAddStudentButtonClicked)
        binding?.buttonTwo?.setOnClickListener(::onAddStudentButtonClicked)
        binding?.buttonThree?.setOnClickListener(::onAddStudentButtonClicked)
        binding?.buttonFour?.setOnClickListener(::onAddStudentButtonClicked)

        addPinkFragment(fragmentOne)
    }

    fun onAddStudentButtonClicked(view: View) {
        val tag = (view as? Button)?.tag as? String ?: return
        when (tag) {
            "1" -> addPinkFragment(fragmentOne)
            "2" -> addPinkFragment(fragmentTwo)
            "3" -> addPinkFragment(fragmentThree)
            "4" -> addPinkFragment(fragmentFour)
        }
    }

    fun addPinkFragment(fragment: Fragment?) {

        fragment?.let {
            supportFragmentManager.beginTransaction().apply {

                displayedFragment?.let { existingFragment ->
                    remove(existingFragment)
                }

                add(R.id.frame_layout, it)
                addToBackStack("TAG")
                commit()
            }

            displayedFragment = it
        }
    }
}