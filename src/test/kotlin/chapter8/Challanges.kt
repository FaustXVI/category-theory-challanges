package chapter8

import assertk.assertions.isEqualTo
import kotlin.test.Test

class Challanges {
    @Test
    fun pairIsABifunctor() {
        val myPair = MyPair(2, "toto")
        assertk.assert(myPair.bimap({ it + 1 }, { it + "test" })).isEqualTo(MyPair(3, "tototest"))
    }
}

interface Bifunctor<A, B> {
    fun <C, D> bimap(f: (A) -> C, g: (B) -> D): Bifunctor<C, D>
}

data class MyPair<A, B>(val first: A, val second: B) : Bifunctor<A, B> {
    override fun <C, D> bimap(f: (A) -> C, g: (B) -> D): MyPair<C, D> = MyPair(f(first), g(second))
}
