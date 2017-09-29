package de.ad.retrofitwithcoroutines

import kotlinx.coroutines.experimental.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await

class ServiceTest {
    lateinit var service: GitHubService

    @BeforeEach
    internal fun setUp() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(GitHubService::class.java)
    }

    @Test
    internal fun should_callServiceWithRx() {
        service.listReposWithRx("a11n").subscribe { 
            repos -> 
                repos.forEach(::println) 
        }
    }

    @Test
    internal fun should_callServiceWithCoroutine() {
        runBlocking { 
            val repos = service.listReposWithCoroutines("a11n").await()
            repos.forEach(::println)
        }
    }
}