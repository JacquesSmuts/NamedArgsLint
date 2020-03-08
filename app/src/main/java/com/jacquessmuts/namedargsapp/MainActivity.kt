package com.jacquessmuts.namedargsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    class AgeThing(age: Int)
    data class User(val name: String, val surname: String, val age: AgeThing)

    fun test() {
        val namedUser = User("Namey", "McNameface", AgeThing(30))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
