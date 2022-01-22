package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val displayName: TextView = this.findViewById(R.id.displayName)
        val githubUsername: TextView = this.findViewById(R.id.githubUsername)
        val imageView: ImageView = this.findViewById(R.id.profilePicture)

        displayName.text = intent.getStringExtra("displayName")
        githubUsername.text = "@"+intent.getStringExtra("githubUsername")
        Picasso.get().load(intent.getStringExtra("avatarUrl")).into(imageView)
    }
}