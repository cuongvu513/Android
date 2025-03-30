package com.example.doitien

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtAmount = findViewById<EditText>(R.id.edtAmount)
        val spnFrom = findViewById<Spinner>(R.id.spnFrom)
        val spnTo = findViewById<Spinner>(R.id.spnTo)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val txtExchangeRate = findViewById<TextView>(R.id.txtExchangeRate)
        val txtFromSymbol = findViewById<TextView>(R.id.txtFromSymbol)
        val txtToSymbol = findViewById<TextView>(R.id.txtToSymbol)
        val btnConvert = findViewById<Button>(R.id.btnConvert)

        val currencies = mapOf(
            "USD" to "$", "EUR" to "€", "GBP" to "£", "JPY" to "¥", "VND" to "₫"
        )

        val exchangeRates = mapOf(
            "USD" to 1.0, "EUR" to 0.92, "GBP" to 0.77, "JPY" to 150.6, "VND" to 25575.0
        )

        val currencyList = currencies.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnFrom.adapter = adapter
        spnTo.adapter = adapter

        spnFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                txtFromSymbol.text = currencies[currencyList[position]]
                updateExchangeRate(spnFrom, spnTo, txtExchangeRate, exchangeRates)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spnTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                txtToSymbol.text = currencies[currencyList[position]]
                updateExchangeRate(spnFrom, spnTo, txtExchangeRate, exchangeRates)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btnConvert.setOnClickListener {
            val amountText = edtAmount.text.toString()
            if (amountText.isNotEmpty()) {
                val amount = amountText.toDouble()
                val fromCurrency = spnFrom.selectedItem.toString()
                val toCurrency = spnTo.selectedItem.toString()
                val result = amount * (exchangeRates[toCurrency]!! / exchangeRates[fromCurrency]!!)
                val decimalFormat = DecimalFormat("#,##0.00")
                txtResult.text = "${currencies[toCurrency]} ${decimalFormat.format(result)}"
            } else {
                Toast.makeText(this, "Vui lòng nhập số tiền", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateExchangeRate(spnFrom: Spinner, spnTo: Spinner, txtExchangeRate: TextView, rates: Map<String, Double>) {
        val fromCurrency = spnFrom.selectedItem.toString()
        val toCurrency = spnTo.selectedItem.toString()
        val rate = rates[toCurrency]!! / rates[fromCurrency]!!
        val decimalFormat = DecimalFormat("#,##0.00")
        txtExchangeRate.text = "Tỷ giá: 1 $fromCurrency = ${decimalFormat.format(rate)} $toCurrency"
    }
}