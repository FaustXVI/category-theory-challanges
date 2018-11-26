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
}

