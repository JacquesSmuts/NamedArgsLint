package com.jacquessmuts.namedargsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    class AgeWrapper(age: Int)
    data class User(val name: String, val surname: String, val age: AgeWrapper)

    fun test() {

        val namedUser = User(
            name = "Namey",
            surname = "McNameface",
            AgeWrapper(30)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
