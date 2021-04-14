package com.chopchop.tupoytest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.chopchop.tupoytest.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this,
            "ca-app-pub-7011036469996496~6067835236")

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-7011036469996496/1681867669"
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        val start:Button = findViewById(R.id.button)
        start.setOnClickListener {
            val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
            if(mySharedPreferences.contains("id")) {
                val editor: SharedPreferences.Editor = mySharedPreferences.edit()
                editor.putInt("id", 0)
                editor.putInt("result", 0)
                editor.apply()
            }
            val intent = Intent(this,quiz::class.java)
            startActivity(intent)
        }
;
    }

    fun restartButtonClick(view: View) {
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = mySharedPreferences.edit()
        editor.putInt("id", 0)
        editor.putInt("result", 0)
        editor.apply()
    }

    fun adHelpAutor(view: View){

        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
    }
}