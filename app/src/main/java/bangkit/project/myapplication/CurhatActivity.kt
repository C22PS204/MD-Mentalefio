package bangkit.project.myapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_curhat.*
import kotlinx.android.synthetic.main.activity_home.homeButton
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

const val REQUEST_CODE = 200
class CurhatActivity : AppCompatActivity(), Timer.OnTimerTickListener{

    private var permission = arrayOf(android.Manifest.permission.RECORD_AUDIO)
    private var permissionGranted = false
    private lateinit var amplitudes: ArrayList<Float>
    private  lateinit var recorder: MediaRecorder
    private var dirPath = ""
    private var filename = ""
    private var isRecording = false
    private var isPaused = false
    private var duration = ""
    private lateinit var timer : Timer


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curhat)
        supportActionBar?.hide()
        if (!checkPermision()) ActivityCompat.requestPermissions(this,permission, REQUEST_CODE)
        timer = Timer(this)
        homeButton.setOnClickListener{
            Intent(this@CurhatActivity, HomeActivity::class.java).also {
                startActivity(it)
            }
        }
        buttonRecord.setOnClickListener{
            when{
                isPaused ->  resumeRecording()
                isRecording -> pausedRecording()
                else -> startRecording()
            }
            buttonDone.visibility = VISIBLE
        }
        buttonDone.setOnClickListener{
            stopRecording()
            Toast.makeText(this,"Record gained", Toast.LENGTH_SHORT).show()
            Intent(this@CurhatActivity, RequestActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun checkPermision():Boolean{
        var first = ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO)
        var second = ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return first == PackageManager.PERMISSION_GRANTED && second == PackageManager.PERMISSION_GRANTED

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED

    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun resumeRecording(){
        recorder.resume()
        isPaused = false
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun pausedRecording(){
        recorder.pause()
        isPaused = true
    }

    private fun stopRecording() {
        recorder.apply {
            stop()
            release()
        }
        isPaused = false
        isRecording = false

        amplitudes = waveformView.clear()
    }

    private fun startRecording(){
        if (!permissionGranted){
            ActivityCompat.requestPermissions(this,permission, REQUEST_CODE)
            return
        }
        //record start
        recorder = MediaRecorder()
        dirPath = "${externalCacheDir?.absolutePath}/"
        var simpleDateFormat = SimpleDateFormat("yyyy.MM.DD_hh.mm.ss")
        var date = simpleDateFormat.format(Date())
        filename = "audio_record_$date"
        recorder.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile("$dirPath$filename.mp3")
            try{
                prepare()
            }catch (e: IOException){}
            start()
        }
        isRecording = true
        isPaused = false
        timer.start()
    }

    override fun OntimerTick(duration: String) {
        this.duration = duration.dropLast(3)
        waveformView.AddAmplitude(recorder.maxAmplitude.toFloat())
    }

}