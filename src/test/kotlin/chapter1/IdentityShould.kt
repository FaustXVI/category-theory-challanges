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