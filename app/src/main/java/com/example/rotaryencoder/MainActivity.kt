package com.example.rotaryencoder

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hardkernel.odroid.things.contrib.RotaryEncoder.IncrementalRotaryEncoder
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val dtPinName = "16"
    private val clkPinName = "18"
    private val swPinName = "22"


    private var incrementalRotaryEncoder: IncrementalRotaryEncoder? = null
    private lateinit var audioManager: AudioManager


    private val rotaryListener = object : IncrementalRotaryEncoder.RotaryListener {
        override fun cw() {
            increaseVolume()
        }

        override fun ccw() {
            decreaseVolume()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        try {
            incrementalRotaryEncoder =
                IncrementalRotaryEncoder(dtPinName, swPinName, clkPinName, rotaryListener)

            incrementalRotaryEncoder!!.startEncoder()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun increaseVolume() {
        audioManager.adjustStreamVolume(
            AudioManager.STREAM_SYSTEM,
            AudioManager.ADJUST_RAISE,
            AudioManager.FLAG_SHOW_UI
        )
    }

    private fun decreaseVolume() {
        audioManager.adjustStreamVolume(
            AudioManager.STREAM_SYSTEM,
            AudioManager.ADJUST_LOWER,
            AudioManager.FLAG_SHOW_UI
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        incrementalRotaryEncoder?.close()
    }
}