package chapter1

import assertk.assertions.isEqualTo
import kotlin.test.Test

class IdentityShould {

    @Test
    fun returnSameValue() {
        assertk.assert(identity(1)).isEqualTo(1)
        assertk.assert(identity("value")).isEqualTo("value")
    }

    private fun <T> identity(value: T): T = value
}


class CompositionShould {

    @Test
    fun chainTwoFunctions() {
        val times2: (Int) -> Int = { it * 2 }
        val plus5: (Int) -> Int = { it + 5 }
        assertk.assert(compose(times2, plus5)(1)).isEqualTo(7)

        val duplicate: (String) -> String = { it + it }
        val appendA: (String) -> String = { it + "A" }
        assertk.assert(compose(duplicate,appendA)("test")).isEqualTo("testtestA")
    }

}

fun <T>compose(g: (T) -> T, f: (T) -> T): (T) -> T = { f(g(it)) }
