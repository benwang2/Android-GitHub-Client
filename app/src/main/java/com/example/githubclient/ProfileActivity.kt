package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    private val githubAPI = GithubAPI.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val recyclerView: RecyclerView = this.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val displayName: TextView = this.findViewById(R.id.displayName)
        val githubUsername: TextView = this.findViewById(R.id.githubUsername)
        val imageView: ImageView = this.findViewById(R.id.profilePicture)

        val emailRow: TableRow = this.findViewById(R.id.emailRow)
        val email: TextView = this.findViewById(R.id.email)

        val twitterRow: TableRow = this.findViewById(R.id.twitterRow)
        val twitterUsername: TextView = this.findViewById(R.id.twitterUsername)

        displayName.text = intent.getStringExtra("displayName") ?: intent.getStringExtra("githubUsername")
        githubUsername.text = "@${intent.getStringExtra("githubUsername")}"
        Picasso.get().load(intent.getStringExtra("avatarUrl")).into(imageView)

        emailRow.visibility = if (intent.getStringExtra("email") == null ) View.GONE else View.VISIBLE
        email.text = intent.getStringExtra("email")

        twitterRow.visibility = if (intent.getStringExtra("twitterUsername") == null) View.GONE else View.VISIBLE
        twitterUsername.text = intent.getStringExtra("twitterUsername")

        val numFollowers: TextView = this.findViewById(R.id.followersValue)
        val numFollowing: TextView = this.findViewById(R.id.followingValue)
        val numStarred: TextView = this.findViewById(R.id.starredValue)
        val numRepos: TextView = this.findViewById(R.id.reposValue)

        numFollowers.text = intent.getStringExtra("numFollowers")
        numFollowing.text = intent.getStringExtra("numFollowing")
        numRepos.text = intent.getStringExtra("numRepos")

        val starredCallback = githubAPI.getStarred(intent.getStringExtra("githubUsername")!!)
        var starredRepos: List<Repository>

        starredCallback.enqueue(object : Callback<List<Repository>> {
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) = TODO("Not yet implemented")
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                starredRepos = response.body()!!
                numStarred.setText(response.body()!!.size.toString() + (if (response.body()!!.size == 100) "+" else ""))
            }
        })

        val followersRegion: ConstraintLayout = this.findViewById(R.id.followersRegion)
        val followersCallback = githubAPI.getFollowers(intent.getStringExtra("githubUsername")!!)
        var followers: List<User>? = null;

        followersCallback.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                followers = response.body()!!
                recyclerView.adapter = ProfileRecyclerAdapter(followers!!)
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) = TODO("Not yet implemented")
        })

        followersRegion.setOnClickListener({
            recyclerView.adapter = ProfileRecyclerAdapter(followers!!)
        })

        val followingRegion: ConstraintLayout = this.findViewById(R.id.followingRegion)
        val followingCallback = githubAPI.getFollowing(intent.getStringExtra("githubUsername")!!)
        var following: List<User>? = null

        followingCallback.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {following = response.body()!!}
            override fun onFailure(call: Call<List<User>>, t: Throwable) = TODO("Not yet implemented")
        })

        followingRegion.setOnClickListener({
            recyclerView.adapter = ProfileRecyclerAdapter(following!!)
        })

    }
}