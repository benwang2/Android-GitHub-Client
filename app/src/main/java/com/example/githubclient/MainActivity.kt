package com.example.githubclient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val githubAPI = GithubAPI.create()

    fun loadProfile(user: User){
        val intent = Intent(this, ProfileActivity::class.java)
        intent.apply {
            putExtra("displayName", user.displayName)
            putExtra("githubUsername", user.username)
            putExtra("avatarUrl", user.avatarUrl)

            putExtra("email", user.email)
            putExtra("twitterUsername", user.twitterUsername)

            putExtra("followersUrl", user.followers)
            putExtra("followingsUrl", user.following)

            putExtra("numFollowers", user.numFollowers.toString())
            putExtra("numFollowing", user.numFollowing.toString())
            putExtra("numRepos",user.numRepos.toString())
        }

        Log.d("dbg","Starting activity.")
        startActivity(intent)
    }

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
                    if (user.message == null){
                        Log.d("dbg","Open intents")
                        loadProfile(user)
                    }
                }
            })
        }

    }
}