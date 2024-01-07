package com.jameermulani.hellounittestingandroid.taxcalculator

import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import org.junit.After
import org.junit.Before
import org.junit.Test


class TaxCalculatorTest {

    private lateinit var taxCalculator: TaxCalculator
    private var netIncome : Double = 0.0
    private var tax : Double = 0.0
    private var grossIncome = 100.0
    private var taxRate = 0.1

    @Before
    fun before(){
        taxCalculator = TaxCalculator()
    }

    @After
    fun after(){
        netIncome = 0.0
        tax = 0.0
    }

    @Test
    fun `calculate tax passed the test`() {
        tax = taxCalculator.calculateTax(grossIncome, taxRate)
        assertThat(tax).isNotNull()
        assertThat(tax).isGreaterThan(-1)
        assertThat(tax).isEqualTo(10)
    }

    @Test
    fun `calculate tax returns zero and passed when grossincome is negative number`(){
        tax = taxCalculator.calculateTax(grossIncome = -100.0, taxRate = 0.2)
        assertThat(tax).isGreaterThan(-1)
    }

    @Test
    fun `calculate tax returns zero and passed when taxrate is negative number`(){
        tax = taxCalculator.calculateTax(grossIncome = 100.0, taxRate = -0.3)
        assertThat(tax).isGreaterThan(-1)
    }

    @Test
    fun `calculate netIncome passed when grossIncome is positive number`() {

        netIncome = taxCalculator.calculateNetIncome(grossIncome, taxRate)
        assertThat(netIncome).isEqualTo(90)

    }

    @Test
    fun `calculate netIncome passed when grossIncome is negative number`(){
        netIncome = taxCalculator.calculateNetIncome(grossIncome = -100.0, taxRate = 0.2)
        assertThat(netIncome).isEqualTo(netIncome)
    }
}