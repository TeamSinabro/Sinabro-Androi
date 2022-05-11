package com.sinabro.presentation.ui.pronouncelearning

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.util.Base64.NO_WRAP
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sinabro.R
import com.sinabro.databinding.ActivityPronounceLearningBinding
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.pronouncelearning.viewmodel.PronounceViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.and
import timber.log.Timber
import java.util.*


@AndroidEntryPoint
class PronounceLearningActivity :
    BaseActivity<ActivityPronounceLearningBinding>(R.layout.activity_pronounce_learning) {
    private val pronounceViewModel: PronounceViewModel by viewModels()
    private val maxLenSpeech = 16000 * 45
    private val speechData = ByteArray(maxLenSpeech * 2)
    private lateinit var audio: AudioRecord
    var bufferSize = 0
    var lenSpeech = 0
    var forceStop = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAudio()
        goAnswer()
        clickRecordBtn()
        saveRecording()
        sendData()
    }

    //사용자 점수 확인
    private fun goAnswer() {
        binding.textPronounceLearningRecStop.setOnClickListener {
            val intent = Intent(this, PronounceLearningAnswerActivity::class.java)
            startActivity(intent)
        }
    }

    //녹음 버튼 클릭 이벤트
    private fun clickRecordBtn() {
        binding.textPronounceLearningRec.setOnClickListener {
            pronounceViewModel.recording.value = true
        }

        binding.textPronounceLearningRecStop.setOnClickListener {
            stopRecording()
        }
    }

    //audio 초기화
    private fun initAudio() {
        bufferSize = AudioRecord.getMinBufferSize(
            16000,  // sampling frequency
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        audio = when (PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) -> {
                AudioRecord(
                    MediaRecorder.AudioSource.VOICE_RECOGNITION,
                    16000,  // sampling frequency
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize
                )
            }
            else -> {
                return
            }

        }
    }

    //녹음 플로우
    private fun saveRecording() {
        pronounceViewModel.recording.observe(this) {
            Timber.d("record 중지")
            if (it) {
                audio.startRecording()
            } else {
                lenSpeech = 0
                val inBuffer = ShortArray(bufferSize)
                val ret = audio.read(inBuffer, 0, bufferSize)
                Timber.d("저장 $ret")
                for (i in 0 until ret) {
                        Timber.d("저장 계속 수행")
                        if (lenSpeech >= maxLenSpeech) {
                            forceStop = true
                            break
                        }
                        speechData[lenSpeech * 2] = (inBuffer[i] and 0x00FF).toByte()
                        speechData[lenSpeech * 2 + 1] = ((inBuffer[i] and 0xFF00 shr 8).toByte())
                        lenSpeech++
                    }
                Timber.d("저장 종료")
                audio.stop()
                audio.release()
            }
        }
    }

    //녹음 중지
    private fun stopRecording() {
        pronounceViewModel.recording.value = false
        postPronounce()
    }

    //평가 서버 통신
    private fun postPronounce() {
        val audioContent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(speechData)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        Timber.d("audioContent $audioContent")
        pronounceViewModel.postPronounce(
            PronouncePostItem(
                "c2848d62-dd79-4f12-bdbe-2288528d5669",
                "korean",
                "이것은 발음 평가 연습",
                audioContent
            )
        )
    }

    //점수 데이터 넘겨 주기
    private fun sendData(){
        pronounceViewModel.pronounceData.observe(this){
            val intent = Intent(this, PronounceLearningAnswerActivity::class.java)
            intent.putExtra("pronounceScore", it.result.toString())
            startActivity(intent)
            finish()
        }

    }
}