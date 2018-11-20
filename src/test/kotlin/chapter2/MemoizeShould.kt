package chapter2

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isLessThan
import java.time.Duration
import java.time.LocalTime
import kotlin.test.Test

class MemoizeShould {

    @Test
    fun cacheResult() {
        val square: (Int) -> Int = { it * it }
        checkMemoization(square, 2)
        val toDouble: (String) -> Double = { it.toDouble() }
        checkMemoization(toDouble, "2.0")
    }

    private fun <A, B> checkMemoization(function: (A) -> B, parameter: A) {
        val executionDuration = Duration.ofMillis(100)
        val slowedFunction: (A) -> B = { n -> Thread.sleep(executionDuration.toMillis()); function(n) }
        val memoizeFunction = memoize(slowedFunction)
        val firstResult = memoizeFunction(parameter)
        val startTime = LocalTime.now()
        val secondResult = memoizeFunction(parameter)
        val secondExecutionDuration = Duration.between(startTime, LocalTime.now())
        assert(secondExecutionDuration).isLessThan(executionDuration)
        assert(secondResult).isEqualTo(firstResult)
    }

}


