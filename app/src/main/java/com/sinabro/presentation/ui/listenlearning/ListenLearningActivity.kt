package com.sinabro.presentation.ui.listenlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import com.kakao.sdk.newtoneapi.TextToSpeechClient
import com.kakao.sdk.newtoneapi.TextToSpeechListener
import com.kakao.sdk.newtoneapi.TextToSpeechManager
import com.sinabro.R
import com.sinabro.databinding.ActivityListenLearningBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.listenlearning.viewmodel.ListenLearningViewModel
import com.sinabro.shared.util.SinabroShareData
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@AndroidEntryPoint
class ListenLearningActivity : BaseActivity<ActivityListenLearningBinding>(R.layout.activity_listen_learning) {
    private val listenLearningViewModel : ListenLearningViewModel by viewModels()
    private lateinit var ttsClient : TextToSpeechClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSpeechRecognize()
        initSentenceData()
        clickListenTTS()
        checkLoading()
        clickAnswerCheck()
    }
    //음성인식 음성합성 초기화
    private fun initSpeechRecognize(){
        SpeechRecognizerManager.getInstance().initializeLibrary(this)
        TextToSpeechManager.getInstance().initializeLibrary(this)
    }
    //듣기 이벤트(TTS)
    private fun clickListenTTS(){
        binding.textListenSentence.setOnClickListener {
            //TTS 클라이언트 생성
            ttsClient = TextToSpeechClient.Builder()
                .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)     // 음성합성방식
                .setSpeechSpeed(1.0)            // 발음 속도(0.5~4.0)
                .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_READ_CALM)  //TTS 음색 모드 설정(여성 차분한 낭독체)
                .setListener(object : TextToSpeechListener {
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
            val text = listenLearningViewModel.sentence.value
            ttsClient.play(text)
        }
    }
    //문장 데이터 받아오기
    private fun initSentenceData(){
        val data = SinabroShareData
        showLoading()
        listenLearningViewModel.getSentenceData(data.publisher, data.subject, data.chapter)
    }

    //사용자 문장 입력후 정답 확인
    private fun clickAnswerCheck(){
        binding.textListenLearningOk.setOnClickListener {
            val intent = Intent(this, ListenLearningAnswerActivity::class.java )
            intent.apply{
                putExtra("userAnswer", binding.etListenSentence.text)
                putExtra("answer", listenLearningViewModel.sentence.value)
            }
            startActivity(intent)
        }
    }


    //로딩바
    private fun checkLoading(){
        listenLearningViewModel.onLoadingEnd.observe(this){
            dismissLoading()
        }
    }

}