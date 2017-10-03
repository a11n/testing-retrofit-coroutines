package de.ad.retrofitwithcoroutines

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.mock.Calls
import ru.gildor.coroutines.retrofit.await


class TestServiceTest {
    val expectedRepos = listOf(
            Repo("1", "dummy-repo-1"),
            Repo("2", "dummy-repo-2"))

    @Mock
    lateinit var service: GitHubService

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)

        whenever(service.listReposWithCoroutines(any())).
                thenReturn(Calls.response(expectedRepos))
    }

    @Test
    internal fun should_doSomethingWithRemoteDataFetchedWithCoroutines() {
        val actualRepos = runBlocking { service.listReposWithCoroutines("a11n").await() }

        actualRepos shouldEqual expectedRepos
    }
}