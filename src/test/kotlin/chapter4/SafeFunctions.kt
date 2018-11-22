package chapter4

import assertk.assert
import assertk.assertions.isEqualTo
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

}