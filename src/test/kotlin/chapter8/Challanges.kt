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

data class MyPair(val first: Int, val second: String) {
    fun bimap(f: (Int) -> Int, g: (String) -> String): MyPair = MyPair(f(first), g(second))
}
