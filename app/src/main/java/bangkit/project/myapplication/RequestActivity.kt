package bangkit.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.homeButton
import kotlinx.android.synthetic.main.activity_request.*

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)
        supportActionBar?.hide()
        homeButton.setOnClickListener{
            Intent(this@RequestActivity, HomeActivity::class.java).also {
                startActivity(it)
            }
        }

        noButton.setOnClickListener{
            Intent(this@RequestActivity, HomeActivity::class.java).also {
                startActivity(it)
            }
        }

        yesButton.setOnClickListener{
            Intent(this@RequestActivity, RecomendationActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}