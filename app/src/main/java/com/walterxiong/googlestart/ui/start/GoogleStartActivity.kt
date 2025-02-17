package com.walterxiong.googlestart.ui.start

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.walterxiong.googlestart.R


class GoogleStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_google_start)

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val intent = Intent()
            .addCategory(Intent.CATEGORY_DEFAULT)
            .setComponent(
                ComponentName(
                    "com.miui.securitycenter",
                    "com.miui.googlebase.ui.GmsCoreSettings"
                )
            )
        startActivity(intent)
        finish()
    }
}