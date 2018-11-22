package chapter4

import assertk.assert
import assertk.assertions.isEqualTo
import kotlin.test.Test

class SafeReciprocal {

    @Test
    fun shouldGiveReciprocalValue() {
        assert(safeReciprocal(2.0).value).isEqualTo(1.0 / 2.0)
    }


}