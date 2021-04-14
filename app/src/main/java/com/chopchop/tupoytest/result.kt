package com.chopchop.tupoytest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.chopchop.tupoytest.R

class result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val mySharedPreferences: SharedPreferences = getSharedPreferences("prefer", Context.MODE_PRIVATE)
        var result:Double = 0.0;
        if(mySharedPreferences.contains("result"))
            result = mySharedPreferences.getInt("result", 0).toDouble()

        val textView6: TextView = findViewById(R.id.textView6)
        "Правильных ответов ${(result/25).toInt()} : ${(result /25 *100).toInt()}%".also { textView6.text = it }
        var resultString:String = "";
        resultString = when {
            result /25 *100 == 100.0 -> "Вы абсолютно не тупой!"
            result /25 *100 > 80.0 -> "Вы немного туповат!"
            result /25 *100 > 60.0 -> "Ещё не все потеряно!"
            result /25 *100 > 40.0 -> "Вам стоит задуматься!"
            else -> "Всё печально, вы примат!"
        }

        val textView8: TextView = findViewById(R.id.textView8)
        "Поздравляем! \n\n$resultString".also { textView8.text = it }
    }
    fun backBtn_Click(v: View){
        finish();
    }
}