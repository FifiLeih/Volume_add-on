package com.example.rotaryencoder

import android.os.Build

object BoardDefaults {
    private const val DEVICE_ODROIDN2 = "odroidn2"
    private const val DEVICE_ODROIDN2L = "odroidn2l"
    private const val DEVICE_ODROIDC4 = "odroidc4"
    private const val DEVICE_ODROIDM1 = "odroidm1"
    private const val DEVICE_ODROIDM1S = "odroidm1s"
    val servoMotorPwm: String
        /**
         * Return the preferred PWM Pin for each board.
         */
        get() = when (Build.DEVICE) {
            DEVICE_ODROIDM1, DEVICE_ODROIDM1S -> "7"
            DEVICE_ODROIDC4, DEVICE_ODROIDN2, DEVICE_ODROIDN2L -> "12"
            else -> throw IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE)
        }
    val dtPin: String
        /**
         * Return the preferred DT GPIO Pin for each board.
         */
        get() = when (Build.DEVICE) {
            DEVICE_ODROIDM1, DEVICE_ODROIDM1S -> "12"
            DEVICE_ODROIDC4, DEVICE_ODROIDN2, DEVICE_ODROIDN2L -> "13"
            else -> throw IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE)
        }
    val swPin: String
        get() = when (Build.DEVICE) {
            DEVICE_ODROIDM1, DEVICE_ODROIDM1S -> "16"
            DEVICE_ODROIDC4, DEVICE_ODROIDN2, DEVICE_ODROIDN2L -> "11"
            else -> throw IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE)
        }
    val clkPin: String
        get() = when (Build.DEVICE) {
            DEVICE_ODROIDM1, DEVICE_ODROIDM1S -> "18"
            DEVICE_ODROIDC4, DEVICE_ODROIDN2, DEVICE_ODROIDN2L -> "7"
            else -> throw IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE)
        }
}
