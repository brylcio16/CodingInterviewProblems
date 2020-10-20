package com.example.problem1

import java.util.*

class Problem1 {
    companion object {
        fun rob(housesMoney: Vector<Int>?): Int {
            if (housesMoney == null || housesMoney.size == 0)
                return 0
            if (housesMoney.size == 1)
                return housesMoney[0]

            val result = ArrayList<Int>()

            result.add(housesMoney[0])
            result.add(Math.max(housesMoney[0], housesMoney[1]))

            for (i in 2 until housesMoney.size)
                result.add(Math.max(housesMoney[i] + result[i-2], result[i-1]))

            return result.last()
        }
    }
}
