package com.wolking.whattheevent.data.event.repositories

import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.gson.Gson
import com.wolking.whattheevent.data.core.ApiService
import com.wolking.whattheevent.data.core.Resource
import com.wolking.whattheevent.data.shared.CoroutineDispatchersImpl
import com.wolking.whattheevent.domain.event.repository.EventRepository
import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import com.wolking.whattheevent.utils.MockResponseFileReader
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventRepositoryTest {
    private val mockWebServer = MockWebServer()
    private val port = 8000

    lateinit var apiService: ApiService
    private lateinit var coroutineDispatchers: CoroutineDispatchers
    lateinit var eventRepository: EventRepository

    @Before
    fun init() {
        mockWebServer.start(port)
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)
        coroutineDispatchers = CoroutineDispatchersImpl()

        eventRepository = EventRepositoryImpl(apiService, coroutineDispatchers)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulEventsResponse() = runBlocking {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(MockResponseFileReader("events.json").content)
            }
        }

        eventRepository.getEvents().collect {
            when (it) {
                is Resource.Success -> {
                    assert(true)
                }
                is Resource.Error -> {
                    assert(false)
                }
                else -> {}
            }
        }
    }

    @Test
    fun testSuccessfulEventResponse() = runBlocking {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(MockResponseFileReader("event.json").content)
            }
        }

        eventRepository.getEventById(1).collect {
            when (it) {
                is Resource.Success -> {
                    assert(true)
                }
                is Resource.Error -> {
                    assert(false)
                }
                else -> {}
            }
        }
    }
}