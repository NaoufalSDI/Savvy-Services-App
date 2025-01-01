package com.example.savvy.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.savvy.R

class ActivitySplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashLogo = findViewById<ImageView>(R.id.splash_logo)

        val fadeIn = AlphaAnimation(0.5f, 1f).apply {
            duration = 1000
            fillAfter = true
        }

        splashLogo.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            val fadeOut = AlphaAnimation(1f, 0f).apply {
                duration = 1200
                fillAfter = true
            }
            splashLogo.startAnimation(fadeOut)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            }, 500)
        }, 1000)
    }
}
