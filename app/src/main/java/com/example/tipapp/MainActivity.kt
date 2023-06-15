package com.example.tipapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringText = binding.costOfService.text.toString()
        val cost = stringText.toDoubleOrNull()
        if (cost == null) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.tip20 -> 0.20
            R.id.tip18 -> 0.18
            else -> {
                0.15
            }
        }
        var tipValue= cost*tipPercentage
        tipValue= ceil(tipValue)
        displayTip(tipValue)
    }

    private fun displayTip(tip: Double) {
        val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }

}