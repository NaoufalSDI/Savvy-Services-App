package com.example.savvy.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.savvy.R

class ActivitySplash : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

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
                val isFirstTime = sharedPreferences.getBoolean("is_first_time", true)

                if (isFirstTime) {
                    startActivity(Intent(this, OnBoardingActivity::class.java))
                    sharedPreferences.edit().putBoolean("is_first_time", false).apply()
                } else {
                    startActivity(Intent(this, SignInActivity::class.java))
                }
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            }, 500)
        }, 1000)
    }
}