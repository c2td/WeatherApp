package com.pm.weatherapp

object Utils {

    fun getImageResource(phenomenon: String): Int {
        return when {
            Regex("Clear|Few").containsMatchIn(phenomenon) -> R.drawable.sun
            Regex("Variable clouds|spells").containsMatchIn(phenomenon) -> R.drawable.cloudy
            Regex("snow|snow shower|sleet").containsMatchIn(phenomenon) -> R.drawable.snow
            Regex("rain|shower").containsMatchIn(phenomenon) -> R.drawable.rain
            else -> R.drawable.cloud
        }
    }

    fun getWeekDay(day: Int) : String {
        return when(day) {
            1 -> "Mon"
            2 -> "Tue"
            3 -> "Wed"
            4 -> "Thu"
            5 -> "Fri"
            6 -> "Sat"
            else -> "Sun"
        }
    }

    fun getWords(inputNumber: Int): String {

        val onesArray = ("zero one two three four five six seven eight nine ten " +
                "eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen")
            .split(' ').toTypedArray()
        val tensArray = "placeholder placeholder twenty thirty forty fifty sixty seventy eighty ninety"
            .split(' ').toTypedArray()

        if (inputNumber < 20) {
            return onesArray[inputNumber]
        }

        val ones = onesArray[inputNumber % 10]
        val tens = tensArray[inputNumber / 10]

        return if (ones == "zero") tens else "$tens $ones"
    }

}