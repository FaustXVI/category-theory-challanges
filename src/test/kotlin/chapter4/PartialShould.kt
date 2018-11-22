package chapter4

import assertk.assert
import assertk.assertions.isEqualTo
import java.lang.IllegalArgumentException
import kotlin.test.Test

class PartialShould {

    @Test
    fun behaveLikeNormalFunctionOnDefinedDomain() {
        val f: (Int) -> Int = { it * 2 }
        assert(makeTotal(f)(2).value).isEqualTo(f(2))
    }

    @Test
    fun catchesExceptionsAndReturnsNothing() {
        val f: (Int) -> Int = { throw IllegalArgumentException() }
        assert(makeTotal(f)(2)).isEqualTo(Maybe.Nothing<Int>())
    }

}


