package com.example.tipapp

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
        binding.costOfServiceEditText.setOnKeyListener { v, keyCode, event ->
            handleKeyEvent(v,keyCode)
        }
        binding.calculateBtn.setOnClickListener {
            calculateTip()
        }
    }
    private fun handleKeyEvent(view: View, key:Int): Boolean {
        return if (key== KEYCODE_ENTER) {
            val inputManager=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken,0)
            true
        } else
            false

    }
    private fun calculateTip() {
        val stringText = binding.costOfServiceEditText.text.toString()
        val cost = stringText.toDoubleOrNull()
        if (cost == null) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.tip20 -> 0.20
            R.id.tip18 -> 0.18
            R.id.tip18 -> 0.18
            else -> {
                0.0
            }
        }
        if (tipPercentage > 0.0) {
            var tipValue = cost * tipPercentage
            if (binding.switchRound.isChecked) {
                tipValue = ceil(tipValue)
            }
            displayTip(tipValue)
        } else {
            Toast.makeText(this, "Select Rating", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }

}