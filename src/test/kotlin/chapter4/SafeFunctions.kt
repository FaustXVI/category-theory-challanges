package chapter4

import assertk.assert
import assertk.assertions.isEqualTo
import kotlin.math.sqrt
import kotlin.test.Test

class SafeFunctions {

    @Test
    fun shouldGiveReciprocalValue() {
        assert(safeReciprocal(2.0).value).isEqualTo(1.0 / 2.0)
    }

    @Test
    fun shouldGiveNothingForZero() {
        assert(safeReciprocal(0.0)).isEqualTo(Maybe.Nothing<Double>())
    }

    @Test
    fun shouldGiveRootValue() {
        assert(safeRoot(2.0).value).isEqualTo(sqrt(2.0))
    }

    @Test
    fun shouldGiveNothingForNegativeValues() {
        assert(safeRoot(-2.0)).isEqualTo(Maybe.Nothing<Double>())
    }


}