package com.example.eclecticbank.data.api

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.eclecticbank.model.User
import retrofit2.http.GET

interface ApiService {
    @Mock
    @MockResponse(body = "{\"bankName\":\"Absa\", \"accountNumber\":\"12345\", \"customerPhoneNumber\":\"0786222283\"}")
    @GET("data")
    suspend fun fetchData(): User

}