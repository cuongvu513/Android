package com.example.calculator20225270

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textResult: TextView
    private var state: Int = 1
    private var operator: Char? = null
    private var op1: Int = 0
    private var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonCE, R.id.buttonC, R.id.buttonBS,
            R.id.buttoncong, R.id.buttontru, R.id.buttonnhan, R.id.buttonchia,
            R.id.buttonamduong, R.id.buttonphay, R.id.buttonbang
        )

        buttons.forEach {
            findViewById<Button>(it).setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button0 -> addDigit(0)
            R.id.button1 -> addDigit(1)
            R.id.button2 -> addDigit(2)
            R.id.button3 -> addDigit(3)
            R.id.button4 -> addDigit(4)
            R.id.button5 -> addDigit(5)
            R.id.button6 -> addDigit(6)
            R.id.button7 -> addDigit(7)
            R.id.button8 -> addDigit(8)
            R.id.button9 -> addDigit(9)

            R.id.buttoncong -> {
                operator = '+'
                state = 2
            }

            R.id.buttontru -> {
                operator = '-'
                state = 2
            }

            R.id.buttonnhan -> {
                operator = '*'
                state = 2
            }

            R.id.buttonchia -> {
                operator = '/'
                state = 2
            }

            R.id.buttonBS -> backspace()
            R.id.buttonCE -> clearEntry()
            R.id.buttonC -> clearAll()
            R.id.buttonamduong -> toggleSign()

            R.id.buttonbang -> calculateResult()

            R.id.buttonphay -> {
                // Chưa update
            }
        }
    }

    private fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            textResult.text = op1.toString()
        } else {
            op2 = op2 * 10 + c
            textResult.text = op2.toString()
        }
    }

    private fun backspace() {
        if (state == 1) {
            op1 /= 10
            textResult.text = op1.toString()
        } else {
            op2 /= 10
            textResult.text = op2.toString()
        }
    }

    private fun clearEntry() {
        if (state == 1) {
            op1 = 0
            textResult.text = op1.toString()
        } else {
            op2 = 0
            textResult.text = op2.toString()
        }
    }

    private fun clearAll() {
        op1 = 0
        op2 = 0
        state = 1
        operator = null
        textResult.text = "0"
    }

    private fun toggleSign() {
        if (state == 1) {
            op1 = -op1
            textResult.text = op1.toString()
        } else {
            op2 = -op2
            textResult.text = op2.toString()
        }
    }

    private fun calculateResult() {
        var result = 0
        when (operator) {
            '+' -> result = op1 + op2
            '-' -> result = op1 - op2
            '*' -> result = op1 * op2
            '/' -> {
                result = if (op2 != 0) {
                    op1 / op2
                } else {
                    textResult.text = "Error"
                    return
                }
            }
            else -> return
        }

        textResult.text = result.toString()
        // Reset lại cho phép tính tiếp theo
        op1 = result
        op2 = 0
        operator = null
        state = 1
    }
}
