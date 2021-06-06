package com.example.tema3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tema3.fragments.BookFragment
import com.example.tema3.fragments.HomeFragment
import com.example.tema3.interfaces.ActivityFragmentCommunication
import com.example.tema3.models.BookItemElement

class MainActivity : AppCompatActivity(), ActivityFragmentCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        addHomeFragment()
    }

    private fun addHomeFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = HomeFragment::class.java.name
        val addTransaction = transaction.add(
            R.id.main_activity_frame_layout, HomeFragment.newInstance("", ""), tag
        )

        addTransaction.commit()
    }

    override fun addBookDescriptionFragment(bookItemElement: BookItemElement) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = BookFragment::class.java.name
        val replaceTransaction = transaction.replace(
            R.id.main_activity_frame_layout,
            BookFragment.newInstance("", "", bookItemElement),
            tag
        )

        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }
}