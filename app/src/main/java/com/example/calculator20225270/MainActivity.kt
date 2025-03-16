package com.example.calculator20225270

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView
    var state : Int =1
    var op: Int = 0;
    var op1: Int = 0;
    var op2: Int = 0;
    var op3: Int = 0;
    var op4: Double = 0.0;
    var opcong: Int = 0;
    var optru: Int = 0;
    var opnhan: Int = 0;
    var opchia: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textResult = findViewById(R.id.text_result)
        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.buttonCE).setOnClickListener(this)
        findViewById<Button>(R.id.buttonC).setOnClickListener(this)
        findViewById<Button>(R.id.buttonBS).setOnClickListener(this)
        findViewById<Button>(R.id.buttonchia).setOnClickListener(this)
        findViewById<Button>(R.id.buttonnhan).setOnClickListener(this)
        findViewById<Button>(R.id.buttontru).setOnClickListener(this)
        findViewById<Button>(R.id.buttoncong).setOnClickListener(this)
        findViewById<Button>(R.id.buttonamduong).setOnClickListener(this)
        findViewById<Button>(R.id.buttonphay).setOnClickListener(this)
        findViewById<Button>(R.id.buttonbang).setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.button0) {
            addDigit(0)
        } else if (id == R.id.button1) {
            addDigit(1)
        } else if (id == R.id.button2) {
            addDigit(2)
        } else if (id == R.id.button3) {
            addDigit(3)
        } else if (id == R.id.button4) {
            addDigit(4)
        } else if (id == R.id.button5) {
            addDigit(5)
        } else if (id == R.id.button6) {
            addDigit(6)
        } else if (id == R.id.button7) {
            addDigit(7)
        } else if (id == R.id.button8) {
            addDigit(8)
        } else if (id == R.id.button9) {
            addDigit(9)
        } else if (id == R.id.buttoncong) {
            opcong = 1
            state = 2
        } else if (id == R.id.buttontru) {
            optru = 1
            state = 2
        } else if (id == R.id.buttonnhan) {
            opnhan = 1
            state = 2
        } else if (id == R.id.buttonchia) {
            opchia = 1
            state = 2
        } else if (id == R.id.buttonBS) {
            backspace()

        }else if (id == R.id.buttonCE) {
            clearentry()

        }else if (id == R.id.buttonC) {
            clearall()

        } else if (id == R.id.buttonamduong) {
            doidau()

        }
        else if (id == R.id.buttonbang) {
            var result = 0
            if (opcong == 1) {
                result = op1 + op2
            } else if (optru == 1) {
                result = op1 - op2
            } else if (opnhan == 1) {
                result = op1 * op2
            } else if (opchia == 1) {
                result = op1 / op2
            }



            textResult.text = "$result"
            state = 1
            op1 = 0
            op2 = 0
            opcong = 0
            optru = 0
            opnhan = 0
            opchia = 0
            op3 == 1

        }
    }

    fun backspace(){
        if (state == 1){
            op1 = op1/10
            textResult.text ="$op1"
        }else {
            op2 = op2/10
            textResult.text= "$op2"
        }
    }
    fun clearentry(){
        if (state == 1){
            op1 = 0
            textResult.text ="$op1"
        }else {
            op2 = 0
            textResult.text= "$op2"
        }
    }
    fun clearall(){
        op1 = 0
        op2 = 0
        state = 1
        textResult.text = "0"
    }
    fun doidau() {
        if (state == 1) {
            op1 = -op1
            textResult.text = "$op1"
        } else {
            op2 = -op2
            textResult.text = "$op2"
        }
    }
    fun addDigit (c:Int){
        if(state ==1){
            op1 = op1*10+c
            textResult.text ="$op1"
        }else {
            op2 = op2*10+c
            textResult.text= "$op2"
        }

    }
}