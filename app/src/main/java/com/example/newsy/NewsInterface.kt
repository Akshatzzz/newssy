package com.example.newsy

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=apple&from=2022-08-17&to=2022-08-17&sortBy=popularity&apiKey=c6d4682a45aa469ebd962d466f4933bc
//https://newsapi.org/v2/top-headlines?country=in&apiKey=c6d4682a45aa469ebd962d466f4933bc

const val BASE_URL="https://newsapi.org/"
const val apiKey="c6d4682a45aa469ebd962d466f4933bc"
interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$apiKey")
    fun getHeadlines(@Query("country")country: String,@Query("page") page: Int):Call<News>

    //fun getHeadlines will make a url like
    //     https://newsapi.org/v2/top-headlines?apiKey=$apiKey&country=in&page=2
}
// we'll call the retrofit object and we'lll make it singleton
object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit: Retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)

    }
}