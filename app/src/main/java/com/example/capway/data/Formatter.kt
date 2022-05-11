package com.example.capway.data

import java.text.DecimalFormat

class Formatter {
    companion object {
        private val percentageFormat: DecimalFormat = DecimalFormat("0.00")

        fun formatDoubleToMoney(double: Double): String {
            return percentageFormat.format(double);
        }
    }
}