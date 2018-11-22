package chapter3

import assertk.assert
import assertk.assertions.isEqualTo
import kotlin.test.Test

class MonoidShould {

    @Test
    internal fun respectLaws() {
        checkMonoid(setOf(true, false), false, Boolean::or)
        checkMonoid(setOf(true, false), true, Boolean::and)
    }

    private fun <T> checkMonoid(values: Set<T>, zero: T, operation: (T, T) -> T) {
        checkNeutral(values, zero, operation)
        checkAssociativity(values, operation)
    }

    private fun <T> checkAssociativity(values: Set<T>, operation: (T, T) -> T) {
        values.forEach { a ->
            values.forEach { b ->
                values.forEach { c ->
                    assert(operation(a, operation(b, c))).isEqualTo(operation(operation(a, b), c))
                }
            }
        }
    }

    private fun <T> checkNeutral(values: Set<T>, zero: T, operation: (T, T) -> T) {
        values.forEach {
            assert(operation(it, zero)).isEqualTo(it)
            assert(operation(zero, it)).isEqualTo(it)
        }
    }

}