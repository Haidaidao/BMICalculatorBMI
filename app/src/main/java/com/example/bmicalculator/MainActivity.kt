package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText=findViewById<EditText>(R.id.etWeight)
        val heightText=findViewById<EditText>(R.id.etHeight)
        val calsButton=findViewById<Button>(R.id.btnCalculator)

        calsButton.setOnClickListener() {
            val weight=weightText.text.toString()
            val height=heightText.text.toString()

            if (validInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2Digits)
            }
        }
    }

    private fun validInput(weight:String,height:String):Boolean {
        when {
            height.isNullOrEmpty()-> {
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            weight.isNullOrEmpty()-> {
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
                return false
            }
        }

        return true
    }

    private fun displayResult(bmi:Float) {
        var resultIndex=findViewById<TextView>(R.id.tvIndex)
        var resultDescription=findViewById<TextView>(R.id.tvResult)
        var info=findViewById<TextView>(R.id.tvInfo)

        resultIndex.text=bmi.toString()

        info.text="(Nomal range in 18.5-24.9)"

        var resultText=""
        var color=0

        when {
            bmi<18.50-> {
                resultText="UnderWeight"
                color=R.color.under_weight
            }
            bmi in 18.50..24.99-> {
                resultText="Healthy"
                color=R.color.normal
            }
            bmi in 25.00..29.99-> {
                resultText="OverWeight"
                color=R.color.over_weight
            }
            bmi>29.99-> {
                resultText="Obese"
                color=R.color.obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text=resultText
    }
}