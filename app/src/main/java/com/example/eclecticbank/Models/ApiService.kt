package com.example.eclecticbank.Models

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @Mock
    @MockResponse(body = "{\"bankName\":\"Absa\", \"accountNumber\":\"12345\", \"customerPhoneNumber\":\"0786222283\"}")
    @GET("data")
//    suspend fun fetchData():Users
    suspend fun fetchData():Users

}