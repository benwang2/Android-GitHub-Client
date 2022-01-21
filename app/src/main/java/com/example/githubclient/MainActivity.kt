package com.example.githubclient

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val githubAPI = GithubAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitUsername: Button = findViewById(R.id.submitUsername)

        submitUsername.setOnClickListener {
            val username: String = findViewById<EditText>(R.id.githubUsername).text.toString()
            val call = githubAPI.getUser(username)
            call.enqueue(object : Callback<User> {

                override fun onFailure(call: Call<User>, t: Throwable) {
                    TODO("NOT IMPLEMENTED")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user: User = response.body() ?: return
                    if (user.message == ""){
                        Toast.makeText(applicationContext, user.displayName, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

    }
}