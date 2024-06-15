package com.example.assessment3.network

import com.example.assessment3.model.Bunga
import com.example.assessment3.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://api.hawa.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BungaApiService {
    @GET("json.php")
    suspend fun getBunga(
        @Query("auth") userId: String
    ): List<Bunga>

    @Multipart
    @POST("json.php")
    suspend fun postBunga(
        @Part("auth") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("json.php")
    suspend fun deleteBunga(
        @Query("auth") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object BungaApi {
    val service: BungaApiService by lazy {
        retrofit.create(BungaApiService::class.java)
    }

    fun getBungaUrl(imageId: String): String {
        return "$BASE_URL$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }