package com.jameermulani.hellounittestingandroid.simpleinterestcalculator

import com.google.common.truth.Truth.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class SimpleInterestCalculatorTest {

    //We wanted to create a module which calculates a interest when principle, interest and tenure has given to it.
    //but in TDD way.

    private lateinit var simpleInterestCalculator : SimpleInterestCalculator

    @Before
    fun setup(){
        simpleInterestCalculator = SimpleInterestCalculator()
    }

    @Test
    fun `returns positive interest when positive principle, tenure and rate of interest is given`(){
        val interest = simpleInterestCalculator.calculateInterest(principle = 100.0, tenure = 12, roi = 1.0 )

        //interest = principle * roi * tenure / 100, thus it should be 12
        assertThat(interest).isEqualTo(12)
    }

    @Test
    fun `returns zero interest when negative principle is passed`(){
        val interest = simpleInterestCalculator.calculateInterest(principle = -100.0, tenure = 12, roi = 1.0)
        assertThat(interest).isGreaterThan(-1)
    }

    @Test
    fun `returns zero interest when tenure is negative or zero`(){
        val interest = simpleInterestCalculator.calculateInterest(principle = 100.0, tenure = -1, roi = 1.0)
        assertThat(interest).isGreaterThan(-1)
    }

    @Test
    fun `returns zero interest when roi is negative or zero`(){
        val interest = simpleInterestCalculator.calculateInterest(principle = 100.0, tenure = 1, roi = -1.0)
        assertThat(interest).isGreaterThan(-1)
    }

    @Test
    fun `returns zero interest when roi, principle and tenure are negative or zero`(){
        val interest = simpleInterestCalculator.calculateInterest(principle = -100.0, tenure = -1, roi = -1.0)
        assertThat(interest).isGreaterThan(-1)
    }


    @After
    fun tearDown(){
//        simpleInterestCalculator = null
    }



}