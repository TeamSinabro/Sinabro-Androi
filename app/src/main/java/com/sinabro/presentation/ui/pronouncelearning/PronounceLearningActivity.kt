package com.sinabro.presentation.ui.pronouncelearning

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sinabro.R
import com.sinabro.databinding.ActivityPronounceLearningBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.pronouncelearning.viewmodel.PronounceViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException
import java.util.*
@AndroidEntryPoint
class PronounceLearningActivity : BaseActivity<ActivityPronounceLearningBinding>(R.layout.activity_pronounce_learning) {
    private val pronounceViewModel : PronounceViewModel by viewModel()


    private var output: String? = null
    private var mediaRecorder : MediaRecorder?=null
    private var state: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goAnswer()
        clickRecordBtn()
    }

    //사용자 점수 확인
    private fun goAnswer(){
        binding.textPronounceLearningRec.setOnClickListener {
            val intent= Intent(this, PronounceLearningAnswerActivity::class.java)
            startActivity(intent)
        }
    }

    //녹음 버튼 클릭 이벤트
    private fun clickRecordBtn(){
        binding.textPronounceLearningRec.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //Permission is not granted
                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions,0)
            } else {
                startRecording()
            }
        }

        binding.textPronounceLearningRecStop.setOnClickListener {
            stopRecording()
        }
    }

    //녹음 시작
    private fun startRecording(){
        //config and create MediaRecorder Object
        val fileName: String = Date().time.toString() + ".mp3"
        output = Environment.getExternalStorageDirectory().absolutePath + "/Download/" + fileName //내장메모리 밑에 위치
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource((MediaRecorder.AudioSource.MIC))
        mediaRecorder?.setOutputFormat((MediaRecorder.OutputFormat.MPEG_4))
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)

        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "레코딩 시작되었습니다.", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException){
            e.printStackTrace()
        } catch (e: IOException){
            e.printStackTrace()
        }
    }
    //녹음 중지
    private fun stopRecording(){
        if(state){
            mediaRecorder?.stop()
            mediaRecorder?.reset()
            mediaRecorder?.release()
            state = false
            Toast.makeText(this, "저장 되었습니다.", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "레코딩 상태가 아닙니다.", Toast.LENGTH_SHORT).show()
        }
    }

    //평가 서버 통신
    private fun postPronounce(){


    }

}