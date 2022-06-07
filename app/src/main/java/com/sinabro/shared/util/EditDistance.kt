package com.sinabro.shared.util

class EditDistance {
    fun getMinimum(val1: Int, val2: Int, val3: Int): Int {
        var minNumber = val1
        if (minNumber > val2) minNumber = val2
        if (minNumber > val3) minNumber = val3
        return minNumber
    }

    fun levenshteinDistance(s: CharArray, t: CharArray): Int {
        val m = s.size
        val n = t.size
        val d = Array(m + 1) {
            IntArray(
                n + 1
            )
        }
        for (i in 1 until m) {
            d[i][0] = i
        }
        for (j in 1 until n) {
            d[0][j] = j
        }
        for (j in 1 until n) {
            for (i in 1 until m) {
                if (s[i] == t[j]) {
                    d[i][j] = d[i - 1][j - 1]
                } else {
                    d[i][j] = getMinimum(d[i - 1][j], d[i][j - 1], d[i - 1][j - 1]) + 1
                }
            }
        }
        return d[m - 1][n - 1]
    }

    fun runAlgorithm(val1: String, val2: String) : Int {
        val stringA = val1.toCharArray()
        val stringB = val2.toCharArray()

        return levenshteinDistance(stringA, stringB)

}
}