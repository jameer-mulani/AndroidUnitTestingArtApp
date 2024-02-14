package com.jameermulani.hellounittestingandroid.simpleinterestcalculator

class SimpleInterestCalculator {
    fun calculateInterest(principle: Double, tenure: Int, roi: Double): Double {
        when {
            principle < 0 -> return 0.0
            tenure < 0 -> return 0.0
            roi < 0 -> return 0.0
        }
        return principle * tenure * roi / 100
    }

}
