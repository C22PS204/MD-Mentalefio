package bangkit.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        voiceButton.setOnClickListener{
            Intent(this@HomeActivity, CurhatActivity::class.java).also {
                startActivity(it)
            }
        }
//        profileButton.setOnClickListener{
//            Intent(this@HomeActivity, ProfileActivity::class.java).also {
//                startActivity(it)
//            }
//        }
    }
}