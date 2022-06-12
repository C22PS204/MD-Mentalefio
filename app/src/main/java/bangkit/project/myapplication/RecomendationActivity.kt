package bangkit.project.myapplication

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bangkit.project.myapplication.api.ApiConfig
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recomendation.*
import retrofit2.Call
import retrofit2.Callback

var link = ""

class RecomendationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendation)
        supportActionBar?.hide()

        getSadSong()
        spoty.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
    }

    private fun getAngrySong() {
        TODO("Not yet implemented")
    }

    private fun getSadSong() {
        val id = "Bearer BQAv3_zIi7z_QTW-CGDDh1mQkSeILwkeAjZDeAQRVK0OmGsHJiTSrLADzpPSz_0rcYXee6zfxo9qP8L-ZasK_Cjq0dzU_AQqg13tyDt-EgYOR44SZu5acGqJxWJsZ0vIShylYtegUqVr1rHvonuLGAJU7B9zSX5F4Qra0GBhMKIKc8wmjjMqZ46uIuWm8bs2zDPQRZoic2CRmToeWm7X9LDcTlIwmmgTsWuT7rNrGOHmz2lB5IkEAwhlPml-upi6wE0BJYN-eQH8oUI"
        val client = ApiConfig.getApiService().getSadPlaylist(id)
            client.enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful){
                    val a =response.body()
                    if (a!=null){
                    }
                    Glide.with(this@RecomendationActivity)
                        .load(a?.images?.get(0)?.url)
                        .into(album)
                    judul.text = a?.name
                    link = a?.externalUrls?.spotify.toString()
                }else{
                    val a = response.body()
                    if (a!=null){
                        Log.e(ContentValues.TAG, "onFailure : ${response.errorBody()}")
                    }
                    Toast.makeText(
                        this@RecomendationActivity,
                        "dapat Gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getFearSong() {
        TODO("Not yet implemented")
    }

    private fun getHappySong() {
        TODO("Not yet implemented")
    }
}