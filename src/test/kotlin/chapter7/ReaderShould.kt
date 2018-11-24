package chapter7

import assertk.assert
import assertk.assertions.isEqualTo
import chapter1.identity
import kotlin.test.Test

class ReaderShould {

    @Test
    fun respectIdentity() {
        val function: (Int) -> Int = { it + 42 }
        val reader = Reader(function)
        assert((reader.fmap(::identity))(13)).isEqualTo(Reader(identity(function))(13))
    }

}

class Reader(val function: (Int) -> Int) {
    fun fmap(g: (Int) -> Int): Reader = this

    operator fun invoke(value: Int): Int = value

}