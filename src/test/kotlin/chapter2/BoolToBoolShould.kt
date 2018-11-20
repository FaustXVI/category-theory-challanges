package chapter2

import assertk.assert
import assertk.assertions.containsAll
import chapter1.identity
import kotlin.test.Test

class BoolToBoolShould {

    @Test
    fun twoFunctionsGenerateAllCombinationsArePossible() {
        val results = mutableSetOf<Pair<Boolean,Boolean>>()
        val functions = listOf<(Boolean) -> Boolean>(
                ::identity,
                Boolean::not
        )

        functions.forEach {
            results += Pair(true,it(true))
            results += Pair(false,it(false))
        }

        assert(results).containsAll(Pair(true, true),
                Pair(true, false),
                Pair(false, true),
                Pair(false, false)
        )
    }
}