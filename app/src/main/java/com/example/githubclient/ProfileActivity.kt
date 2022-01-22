package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val displayName: TextView = this.findViewById(R.id.displayName)
        val githubUsername: TextView = this.findViewById(R.id.githubUsername)
        val imageView: ImageView = this.findViewById(R.id.profilePicture)

        val emailRow: TableRow = this.findViewById(R.id.emailRow)
        val email: TextView = this.findViewById(R.id.email)

        val twitterRow: TableRow = this.findViewById(R.id.twitterRow)
        val twitterUsername: TextView = this.findViewById(R.id.twitterUsername)

        displayName.text = intent.getStringExtra("displayName") ?: intent.getStringExtra("githubUsername")
        githubUsername.text = "@"+intent.getStringExtra("githubUsername")
        Picasso.get().load(intent.getStringExtra("avatarUrl")).into(imageView)

        Log.d("dbg","Email: "+intent.getStringExtra("email"))
        Log.d("dbg","Twitter: "+intent.getStringExtra("twitterUsername"))

        emailRow.visibility = if (intent.getStringExtra("email") == null ) View.GONE else View.VISIBLE
        email.text = intent.getStringExtra("email")

        twitterRow.visibility = if (intent.getStringExtra("twitterUsername") == null) View.GONE else View.VISIBLE
        twitterUsername.text = intent.getStringExtra("twitterUsername")


    }
}