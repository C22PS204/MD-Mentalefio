package bangkit.project.myapplication.api

import bangkit.project.myapplication.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("v1/playlists/3c0Nv5CY6TIaRszlTZbUFk?fields=name%2C%20external_urls(spotify)%2Cimages(url)")
    fun getSadPlaylist(
        @Header("Authorization") token : String,
    ): Call<Response>
}