package com.chopchop.tupoytest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chopchop.tupoytest.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.MobileAds


class quiz : AppCompatActivity() {
    var id: Int = 0;

    lateinit var mAdView : AdView
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        MobileAds.initialize(this,
            "ca-app-pub-7011036469996496~6067835236")


        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-7011036469996496/1681867669"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

// TODO: Add adView to your view hierarchy.

        val quizNumb: TextView = findViewById(R.id.textView3);
        val quizText: TextView = findViewById(R.id.textView2);
        val but3: Button = findViewById(R.id.button3)
        val but4: Button = findViewById(R.id.button4)
        val but5: Button = findViewById(R.id.button5)
        val but6: Button = findViewById(R.id.button6)

        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        if(mySharedPreferences.contains("id"))
            id = mySharedPreferences.getInt("id", 0)

        var result:Int = 0;
        if(mySharedPreferences.contains("result"))
            result = mySharedPreferences.getInt("result", 0)



        //val adView: AdView = findViewById(R.id.adView)
        //adView.adSize = AdSize.BANNER
        //adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        // MobileAds.initialize(this) {}
        //val adRequest = AdRequest.Builder().build()
        //adView.loadAd(adRequest)

        "Вопрос ${id/5+1}  ${result}/25".also { quizNumb.text = it };
        quizText.text = resources.getStringArray(R.array.victorina)[id]
        but3.text = resources.getStringArray(R.array.victorina)[id + 1]
        but4.text = resources.getStringArray(R.array.victorina)[id + 2]
        but5.text = resources.getStringArray(R.array.victorina)[id + 3]
        but6.text = resources.getStringArray(R.array.victorina)[id + 4]




    }

    fun someChooseClick(view: View) {
        val mySharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        val editor: Editor = mySharedPreferences.edit()

        var result:Int = 0;
        var choose: Int = 0;
        when(view.id){
            R.id.button3 ->choose = 1
            R.id.button4 ->choose = 2
            R.id.button5 ->choose = 3
            R.id.button6 ->choose = 4
        }
        if(resources.getIntArray(R.array.otvet)[id/5]==choose){
            if(mySharedPreferences.contains("result"))
                result = mySharedPreferences.getInt("result", 0)
            editor.putInt("result", ++result)
        }

        editor.putInt("id", id + 5)

        editor.apply()
        var intent = intent

        //mInterstitialAd.loadAd(AdRequest.Builder().build())

        if(id == 120){
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
            intent = Intent(this, com.chopchop.tupoytest.result::class.java)
        }
        startActivity(intent)

        if(id/5 == 9 || id/5 == 19 || id/5 == 24)
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
                finish()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        else
            finish()


    }

}