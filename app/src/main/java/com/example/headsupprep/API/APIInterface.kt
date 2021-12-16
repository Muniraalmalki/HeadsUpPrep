package com.example.headsupprep.API

import com.example.headsupprep.Model.Celebrity
import com.example.headsupprep.Model.CelebrityItem
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("/celebrities/")
    fun getAllCelebrity():Call<Celebrity>

    @POST("/celebrities/")
    fun addCelebrity(@Body data: CelebrityItem):Call<CelebrityItem>

    @PUT("/celebrities/{id}")
    fun updateCelebrity(@Path("pk") pk:Int,@Body data:CelebrityItem): Call<CelebrityItem>

    @DELETE("/celebrities/{id}")
    fun deleteCelebrity(@Path("pk")pk: Int):Call<Void>
}