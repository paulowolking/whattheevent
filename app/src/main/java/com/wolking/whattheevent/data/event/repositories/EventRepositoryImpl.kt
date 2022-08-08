package com.wolking.whattheevent.data.event.repositories

import com.wolking.whattheevent.data.core.ApiService
import com.wolking.whattheevent.data.core.Resource
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.domain.event.entities.User
import com.wolking.whattheevent.domain.event.repository.EventRepository
import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EventRepositoryImpl
@Inject constructor(
    private val apiService: ApiService,
    private val coroutineDispatchers: CoroutineDispatchers,
) : EventRepository() {

    override suspend fun getEvents(): Flow<Resource<List<Event>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getEvents()
                val events = arrayListOf<Event>()
                for (notice in response) {
                    events.add(notice.toDomain())
                }
                emit(Resource.Success(events))
            } catch (e: Exception) {
                when (e) {
                    is IOException -> emit(Resource.Error.NoInternetConnection)
                    is HttpException -> emit(Resource.Error.RequestError(e.message()))
                    else -> emit(Resource.Error.GenericError(e))
                }
            }
        }.flowOn(coroutineDispatchers.defaultContext)


    override suspend fun getEventById(id: Int): Flow<Resource<Event>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getEvent(id)
                emit(Resource.Success(response.toDomain()))
            } catch (e: Exception) {
                when (e) {
                    is IOException -> emit(Resource.Error.NoInternetConnection)
                    is HttpException -> emit(Resource.Error.RequestError(e.message()))
                    else -> emit(Resource.Error.GenericError(e))
                }
            }
        }.flowOn(coroutineDispatchers.defaultContext)

    override suspend fun checkIn(user: User): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading())
            try {
                apiService.checkIn(user)
                emit(Resource.Success(true))
            } catch (e: Exception) {
                when (e) {
                    is IOException -> emit(Resource.Error.NoInternetConnection)
                    is HttpException -> emit(Resource.Error.RequestError(e.message()))
                    else -> emit(Resource.Error.GenericError(e))
                }
            }
        }
}