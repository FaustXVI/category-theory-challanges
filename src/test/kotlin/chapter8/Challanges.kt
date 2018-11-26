package chapter8

import assertk.assert
import assertk.assertions.isEqualTo
import kotlin.test.Test

class Challanges {
    @Test
    fun pairIsABifunctor() {
        val myPair = MyPair(2, "toto")
        assert(myPair.bimap({ it + 1 }, { it + "test" })).isEqualTo(MyPair(3, "tototest"))
        assert(myPair.first { it + 1 }).isEqualTo(MyPair(3, "toto"))
        assert(myPair.second { it + "test" }).isEqualTo(MyPair(2, "tototest"))
    }

    @Test
    fun pairIsAFunctor() {
        val myPair = MyPair(2, "toto")
        assert(myPair.fmap { it + "test" }).isEqualTo(MyPair(2, "tototest"))
    }

    @Test
    fun preListIsABifunctor() {
        val emptyPreList: PreList<Int, String> = PreList.Nil()
        assert(emptyPreList.bimap(Int::toString, String::toBoolean)).isEqualTo(PreList.Nil<String, Boolean>())
    }
}

sealed class PreList<A, B> : Bifunctor<A, B> {
    class Nil<A, B>() : PreList<A, B>() {
        override fun <C, D> bimap(f: (A) -> C, g: (B) -> D): Bifunctor<C, D> = Nil()
        override fun equals(other: Any?): Boolean = other is Nil<*, *>
        override fun hashCode(): Int = 0

    }
}

