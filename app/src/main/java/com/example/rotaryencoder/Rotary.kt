package com.example.rotaryencoder

import android.util.Log
import com.google.android.things.contrib.driver.pwmservo.Servo
import com.google.android.things.pio.Gpio
import com.hardkernel.odroid.things.contrib.RotaryEncoder.IncrementalRotaryEncoder
import com.hardkernel.odroid.things.contrib.RotaryEncoder.IncrementalRotaryEncoder.RotaryListener
import java.io.IOException

class Rotary {
    fun volume() {

        val min = -90
        val max = 90

        val mServo: Servo
        val rotaryEncoder: IncrementalRotaryEncoder?

        try {
            mServo = Servo(BoardDefaults.servoMotorPwm)
            mServo.setPulseDurationRange(0.75, 2.6)
            mServo.setAngleRange(min.toDouble(), max.toDouble())
            mServo.setEnabled(true)
            mServo.angle = 40.0
            rotaryEncoder = IncrementalRotaryEncoder(
                BoardDefaults.dtPin,
                BoardDefaults.swPin,
                BoardDefaults.clkPin,
                object : RotaryListener {
                    var duty = 0
                    override fun cw() {
                        duty -= 1
                        if (duty < min) duty = min
                        try {
                            Log.d("cw", "duty - $duty")
                            mServo.angle = duty.toDouble()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                    override fun ccw() {
                        duty += 1
                        if (duty > max) duty = max
                        try {
                            Log.d("ccw", "duty - $duty")
                            mServo.angle = duty.toDouble()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                })
            rotaryEncoder.registerSwitch { gpio: Gpio? ->
                Log.d("sw", "click!")
                true
            }
            val thread = Thread {
                try {
                    rotaryEncoder.startEncoder()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}