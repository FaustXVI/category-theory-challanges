package chapter8

import assertk.assert
import assertk.assertions.isEqualTo
import chapter1.identity
import kotlin.test.Test

class Challanges {
    @Test
    fun pairIsABifunctor() {
        val myPair = MyPair(2, "toto")
        assert(myPair.bimap({ it + 1 }, { it + "test" })).isEqualTo(MyPair(3, "tototest"))
        assert(myPair.first { it + 1 }).isEqualTo(MyPair(3, "toto"))
        assert(myPair.second { it + "test" }).isEqualTo(MyPair(2, "tototest"))
    }
}

interface Bifunctor<A, B> {
    fun <C, D> bimap(f: (A) -> C, g: (B) -> D): Bifunctor<C, D>
    fun <C> first(f: (A) -> C): Bifunctor<C, B> = bimap(f, ::identity)
    fun <D> second(g: (B) -> D): Bifunctor<A, D> = bimap(::identity, g)
}

data class MyPair<A, B>(val first: A, val second: B) : Bifunctor<A, B> {
    override fun <C, D> bimap(f: (A) -> C, g: (B) -> D): MyPair<C, D> = MyPair(f(first), g(second))
}
