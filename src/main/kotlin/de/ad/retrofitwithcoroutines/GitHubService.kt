package de.ad.retrofitwithcoroutines

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun listReposWithCoroutines(@Path("user") user: String): Call<List<Repo>>

    @GET("users/{user}/repos")
    fun listReposWithRx(@Path("user") user: String): Single<List<Repo>>
}

data class Repo(val id: String, val name: String)
