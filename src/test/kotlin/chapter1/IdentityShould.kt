package chapter1

import assertk.assertions.isEqualTo
import kotlin.test.Test

class IdentityShould {

    @Test
    fun returnSameValue() {
        assertk.assert(identity(1)).isEqualTo(1)
    }

    private fun identity(value: Int): Int = value
}