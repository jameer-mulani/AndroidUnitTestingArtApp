package com.jameermulani.hellounittestingandroid.util

import android.content.Context
import android.widget.Toast
import java.time.Duration

object Util {

    const val API_KEY = "41691511-89e5f6a55b8198424e6e28cd3"
    const val BASE_URL = "https://pixabay.com"

}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}