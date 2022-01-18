package com.example.githubclient

import android.os.Bundle
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
            val call = githubAPI.getFollowers(username)
            call.enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {}

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    TODO("NOT IMPLEMENTED")
                }

            })
        }

    }
}