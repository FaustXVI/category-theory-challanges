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

    @Test
    fun haveIdentity() {
        assert(Maybe.identity(2)).isEqualTo(Maybe.Just(2))
    }

    @Test
    fun haveComposition() {
        val f = makeTotal { it * 2 }
        val g = makeTotal { it + 2 }
        assert(Maybe.compose(f, g)(1)).isEqualTo(Maybe.Just(6))
    }

}


