package com.walterxiong.googlestart.utils

import android.widget.Toast
import com.walterxiong.GoogleStartApplication

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(GoogleStartApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(GoogleStartApplication.context, this, duration).show()
}
