package com.example.githubclient

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Repository (
    @SerializedName("name") val name:                   String,
    @SerializedName("watchers_count") val watching:     Int,
    @SerializedName("forks_count") val forked:          Int,
    @SerializedName("stargazers_count") val starred:    Int
)

data class User (
    @SerializedName("message") val message: String,
    
    @SerializedName("login") val username: String,
    @SerializedName("name") val displayName: String,
    @SerializedName("avatar_url") val avatarUrl: String,

    @SerializedName("email") val email: String,
    @SerializedName("twitter_username") val twitterUsername: String,

    @SerializedName("followers") val numFollowers: Int,
    @SerializedName("following") val numFollowing: Int,
    @SerializedName("public_repos") val numRepos: Int,

    @SerializedName("followers_url") val followers: String,
    @SerializedName("following_url") val following: String,
    @SerializedName("repos_url") val repos: String
)

interface GithubAPI {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<User>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<User>>

    @GET("users/{username}/repos")
    fun getRepositories(@Path("username") username: String): Call<List<Repository>>

    companion object {
        val ACCESS_TOKEN = ""
        val BASE_URL = "https://api.github.com/"

        fun create() : GithubAPI {

            val interceptor: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor {
                val request: Request = it.request().newBuilder()
                    .addHeader("Authorization", "token $ACCESS_TOKEN")
                    .build()

                it.proceed(request)
            }

            interceptor.build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(GithubAPI::class.java)

        }
    }
}