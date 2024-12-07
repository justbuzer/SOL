package com.justbuzer.sol

import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import android.content.Intent
import android.speech.SpeechRecognizer

class MainActivity : AppCompatActivity() {

    private lateinit var textViewSpeech: TextView
    private lateinit var buttonMic: ImageButton
    private lateinit var speechRecognizer: SpeechRecognizer

    // List to store speech history
    private val speechHistory = mutableListOf<String>()
    private val maxHistorySize = 4  // Limit the history

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSpeech = findViewById(R.id.textViewSpeech)
        buttonMic = findViewById(R.id.buttonMic)
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        // Intent for speech recognition
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }

        // Handle mic button touch events
        buttonMic.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    textViewSpeech.text = "Listening..."
                    speechRecognizer.startListening(speechRecognizerIntent)
                }
                android.view.MotionEvent.ACTION_UP -> {
                    speechRecognizer.stopListening()
                }
            }
            true
        }

        // Speech recognition listener
        speechRecognizer.setRecognitionListener(object : android.speech.RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {
                textViewSpeech.text = "Error occurred: $error"
            }
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val recognizedText = matches?.get(0) ?: "No speech detected"

                // Add recognized text to history
                if (speechHistory.size >= maxHistorySize) {
                    // Remove the oldest item if history exceeds the limit
                    speechHistory.removeAt(0)
                }

                // Add the new recognized text to the history
                speechHistory.add(recognizedText)

                // Update the TextView to display all history (joined with newlines)
                textViewSpeech.text = speechHistory.joinToString("\n")
            }
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }
}
