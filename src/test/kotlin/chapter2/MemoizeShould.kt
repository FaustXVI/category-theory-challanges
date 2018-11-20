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
        val function: (Int) -> Int = { it * 2 }
        val executionDuration = Duration.ofMillis(100)
        val slowedFunction: (Int) -> Int = { n -> Thread.sleep(executionDuration.toMillis()); function(n) }
        val memoizeFunction = memoize(slowedFunction)
        val startTime = LocalTime.now()
        val firstResult = memoizeFunction(2)
        val secondResult = memoizeFunction(2)
        val totalExecutionDuration = Duration.between(startTime, LocalTime.now())
        assert(totalExecutionDuration).isLessThan(executionDuration + executionDuration)
        assert(secondResult).isEqualTo(firstResult)
    }

}


fun memoize(function: (Int) -> Int): (Int) -> Int {
    val memoizeCache = mutableMapOf<Int, Int>()
    return { memoizeCache.computeIfAbsent(it, function) }
}
