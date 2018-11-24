package chapter7

import assertk.assert
import assertk.assertions.isEqualTo
import chapter1.compose
import chapter1.identity
import kotlin.test.Test

class ReaderShould {

    @Test
    fun changeTypeAfterFmap() {
        val function: (Int) -> Double = { it + 42.0 }
        val reader = Reader(function)
        assert((reader.fmap(Double::toString))(13)).isEqualTo("55.0")
    }

    @Test
    fun respectIdentity() {
        val function: (Int) -> Int = { it + 42 }
        val reader = Reader(function)
        assert((reader.fmap(::identity))(13)).isEqualTo(Reader(identity(function))(13))
    }

}

class Reader<R, A>(val function: (R) -> A) {
    fun <B> fmap(g: (A) -> B): Reader<R, B> = Reader(compose(function, g))

    operator fun invoke(value: R): A = function(value)

}