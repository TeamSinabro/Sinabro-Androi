package com.sinabro.presentation.ui.pronouncelearning

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import com.kakao.sdk.newtoneapi.TextToSpeechClient
import com.kakao.sdk.newtoneapi.TextToSpeechListener
import com.kakao.sdk.newtoneapi.TextToSpeechManager
import com.sinabro.R
import com.sinabro.databinding.ActivityPronounceLearningBinding
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.pronouncelearning.viewmodel.PronounceViewModel
import com.sinabro.shared.util.SinabroShareData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
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
    private lateinit var ttsClient : TextToSpeechClient
    var bufferSize = 0
    var lenSpeech = 0
    var forceStop = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPronounceSentence()
        initSpeechRecognize()
        setPronounceSentence()
        initAudio()
        goAnswer()
        clickRecordBtn()
        checkLoading()
        sendData()
        clickListenTTS()
    }
    //음성인식 음성합성 초기화
    private fun initSpeechRecognize(){
        SpeechRecognizerManager.getInstance().initializeLibrary(this)
        TextToSpeechManager.getInstance().initializeLibrary(this)
    }
    //듣기 이벤트(TTS)
    private fun clickListenTTS(){
        binding.textPronounceLearningListen.setOnClickListener {
            //TTS 클라이언트 생성
            ttsClient = TextToSpeechClient.Builder()
                .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)     // 음성합성방식
                .setSpeechSpeed(1.0)            // 발음 속도(0.5~4.0)
                .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_READ_CALM)  //TTS 음색 모드 설정(여성 차분한 낭독체)
                .setListener(object : TextToSpeechListener{
                    override fun onFinished() {
                        val intSentSize = ttsClient.sentDataSize      //세션 중에 전송한 데이터 사이즈
                        val intRecvSize = ttsClient.receivedDataSize  //세션 중에 전송받은 데이터 사이즈

                        val strInacctiveText = "handleFinished() SentSize : $intSentSize  RecvSize : $intRecvSize"

                        Timber.d("tts $strInacctiveText")
                    }

                    override fun onError(code: Int, message: String?) {
                        Timber.d("tts 오류 $code and $message")
                    }
                })
                .build()
            val text = pronounceViewModel.pronounceSentence.value?.problem ?: ""
            ttsClient.play(text)
        }
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
            saveRecording()
        }

        binding.textPronounceLearningRecStop.setOnClickListener {
            stopRecording()
        }
    }
    //발음 평가 문제 서버통신
    private fun initPronounceSentence(){
        val sinabroData = SinabroShareData
        Timber.d("문제 서버  통신 실행")
        Timber.d("문제 서버 ${sinabroData.publisher}")
        showLoading()
        pronounceViewModel.getPronounceSentence("지학사", "사회", sinabroData.chapter)
    }

    //문제 갱신
    private fun setPronounceSentence(){
        pronounceViewModel.pronounceSentence.observe(this){
            binding.textPronounceLearningExampleSentence.text = it.problem
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
        val job = CoroutineScope(Dispatchers.Default).launch {
            audio.startRecording()
            lenSpeech = 0
            val inBuffer = ShortArray(bufferSize)
            while(isActive){
                val ret = audio.read(inBuffer, 0, bufferSize)
                Timber.d("저장 $ret")
                for (i in 0 until ret) {
                    Timber.d("저장 계속 수행")
                    if (lenSpeech >= maxLenSpeech) {
                        forceStop = true
                        break
                    }
                    speechData[lenSpeech * 2 + 1] = ((inBuffer[i] and 0xFF00 shr 8).toByte())
                    lenSpeech++
                }
            }
            Timber.d("저장 종료")
            audio.stop()
            audio.release()
        }
        pronounceViewModel.recording.observe(this){
            if(it){
                job.start()
            }else{
                job.cancel()
                postPronounce()
            }
        }

    }


    //녹음 중지
    private fun stopRecording() {
        pronounceViewModel.recording.value = false
    }

    //평가 서버 통신
    private fun postPronounce() {
        val audioContent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(speechData)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        Timber.d("audioContent $audioContent")
        showLoading()
        pronounceViewModel.postPronounce(
            PronouncePostItem(
                "c2848d62-dd79-4f12-bdbe-2288528d5669",
                "korean",
                pronounceViewModel.pronounceSentence.value?.problem ?: "",
                audioContent
            )
        )
    }

    //점수 데이터 넘겨 주기
    private fun sendData() {
        pronounceViewModel.pronounceData.observe(this) {
            val intent = Intent(this, PronounceLearningAnswerActivity::class.java)
            intent.putExtra("pronounceScore", it.score.toString())
            startActivity(intent)
            finish()
        }
    }

    //로딩 체크
    private fun checkLoading(){
        pronounceViewModel.onLoadingEnd.observe(this){
            dismissLoading()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TextToSpeechManager.getInstance().finalizeLibrary()
    }
}