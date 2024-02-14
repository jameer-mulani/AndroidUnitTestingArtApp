package com.jameermulani.hellounittestingandroid.taxcalculator

class TaxCalculator {

    fun calculateTax(grossIncome : Double, taxRate : Double) : Double{
        if (grossIncome < 0 || taxRate < 0) return 0.0
        return grossIncome * taxRate
    }

    fun calculateNetIncome(grossIncome: Double, taxRate: Double) : Double{
        return grossIncome - calculateTax(grossIncome, taxRate)
    }

}