package com.polydice.icook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple activity class that displays native ad formats.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        btn_native.setOnClickListener {
            startActivity(Intent(this, NativeAdActivity::class.java))
        }

        btn_banner.setOnClickListener {
            startActivity(Intent(this, BannerAdActivity::class.java))
        }

    }
}