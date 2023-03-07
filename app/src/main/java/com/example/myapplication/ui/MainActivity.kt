package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import com.example.myapplication.ui.fragments.DetailFragment
import com.example.myapplication.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment()
    }

    fun addFragment()  {
        val tr: FragmentTransaction = supportFragmentManager.beginTransaction()
        tr.add(R.id.container_fragment, HomeFragment()).commit()
    }

    fun replaceFragment(dbn: String?) {
        val tr: FragmentTransaction = supportFragmentManager.beginTransaction()
        val frag = DetailFragment()
        val bundle = Bundle()
        bundle.putString("SAT", dbn)
        frag.arguments = bundle
        tr.addToBackStack(null)
        tr.replace(R.id.container_fragment, frag).commit()
    }
}