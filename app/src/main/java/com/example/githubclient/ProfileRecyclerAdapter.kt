package com.example.githubclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProfileRecyclerAdapter(private val profiles: List<User>) : RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val displayName: TextView
        val githubUsername: TextView
        val profilePicture: ImageView
        val followers: TextView
        val following: TextView
        val starred: TextView
        val repositories: TextView


        init {
            // Define click listener for the ViewHolder's View.
            displayName = view.findViewById(R.id.displayName)
            githubUsername = view.findViewById(R.id.githubUsername)
            profilePicture = view.findViewById(R.id.profilePicture)

            followers = view.findViewById(R.id.followersValue)
            following = view.findViewById(R.id.followingValue)
            starred = view.findViewById(R.id.starredValue)
            repositories = view.findViewById(R.id.reposValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.profile_card_layout, parent, false)

        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile: User = profiles[position]

        holder.displayName.text = profile.displayName
        holder.githubUsername.text = profile.username
        Picasso.get().load(profile.avatarUrl).into(holder.profilePicture)
        holder.followers.text = profile.numFollowers.toString()
        holder.followers.text = profile.numFollowing.toString()
        holder.repositories.text = profile.numRepos.toString()

    }

    override fun getItemCount(): Int = profiles.size

}