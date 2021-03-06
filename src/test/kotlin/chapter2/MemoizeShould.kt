package chapter2

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isLessThan
import assertk.assertions.isNotEqualTo
import java.time.Duration
import java.time.LocalTime
import kotlin.random.Random
import kotlin.test.Test

class MemoizeShould {

    @Test
    fun cacheResult() {
        val square: (Int) -> Int = { it * it }
        checkMemoization(square, 2)
        val toDouble: (String) -> Double = { it.toDouble() }
        checkMemoization(toDouble, "2.0")
    }


    @Test
    fun notWorkForRandomGenerator() {
        val randomNumber: (Unit) -> Int = { Random.nextInt() }
        assert(randomNumber(Unit)).isNotEqualTo(randomNumber(Unit))
    }
    @Test
    fun worksForSeededRandomGeneratorBecauseTheGeneratorIsDropped() {
        val seedRandomNumber: (Int) -> Int = { Random(it).nextInt() }
        assert(seedRandomNumber(42)).isEqualTo(seedRandomNumber(42))
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


