package chapter4

import assertk.assert
import assertk.assertions.isEqualTo
import kotlin.test.Test

class PartialShould {

    @Test
    fun behaveLikeNormalFunctionOnDefinedDomain() {
        val f: (Int) -> Int = { it * 2 }
        assert(makeTotal(f)(2).value).isEqualTo(f(2))
    }

}

fun makeTotal(f: (Int) -> Int): (Int) -> Maybe<Int> = { x ->
    Maybe(f(x))
}


data class Maybe<T>(val value: T)


