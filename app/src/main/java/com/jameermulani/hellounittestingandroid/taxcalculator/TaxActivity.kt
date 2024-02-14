package com.jameermulani.hellounittestingandroid.taxcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jameermulani.hellounittestingandroid.R

class TaxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_arts_list)

        val taxCalculator = TaxCalculator()
        val income = 100.0
        val netIncome = taxCalculator.calculateNetIncome(income, 0.1)
        val tax = taxCalculator.calculateTax(income, 0.1)
        println("netincome for $income is $netIncome")
        println("tax for $income is $tax")

    }
}