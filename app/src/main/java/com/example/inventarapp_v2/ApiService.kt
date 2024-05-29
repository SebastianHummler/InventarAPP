package com.example.inventarapp_v2

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("inventory/all")
    fun getAllInventoryItems(): Call<List<InventoryItem>>

    @GET("inventory/{id}")
    fun getInventoryItem(@Path("id") id: Long): Call<InventoryItem>

    @POST("inventory/create")
    fun createInventoryItem(@Body inventoryItem: InventoryItem): Call<InventoryItem>

    @PUT("inventory/{id}")
    fun updateInventoryItem(@Path("id") id: Long, @Body inventoryItem: InventoryItem): Call<InventoryItem>

    @DELETE("inventory/{id}")
    fun deleteInventoryItem(@Path("id") id: Long): Call<Void>
}
