/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.polydice.icook

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.vpon.ads.VponMobileAds
import kotlinx.android.synthetic.main.activity_banner_ad.ad_frame
import kotlinx.android.synthetic.main.activity_banner_ad.refresh_button

class BannerAdActivity : AppCompatActivity() {

    private val bannerAdUnitId = "/40828883/iCook_Android_Test_300x250"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ad)

        VponMobileAds.initialize(this)

        refresh_button.setOnClickListener {
            loadAd()
        }

        loadAd()
    }

    private fun loadAd() {
        val builder = AdLoader.Builder(this, bannerAdUnitId)

        builder.forAdManagerAdView( { adView ->
            ad_frame.removeAllViews()
            ad_frame.addView(adView)
        }, AdSize.MEDIUM_RECTANGLE)

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                refresh_button.isEnabled = true
                val error = """"domain: ${loadAdError.domain}, code: ${loadAdError.code}, message: ${loadAdError.message}"""
                Toast.makeText(
                    this@BannerAdActivity, "Failed to load banner ad with error $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).build()

        adLoader.loadAd(AdManagerAdRequest.Builder().build())
    }
}