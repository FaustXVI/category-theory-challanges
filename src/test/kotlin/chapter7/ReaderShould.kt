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
    fun conserveIdentity() {
        val function: (Int) -> Int = { it + 42 }
        val reader = Reader(function)
        assert((reader.fmap(::identity))(13)).isEqualTo(Reader(identity(function))(13))
    }

    @Test
    fun conserveComposition() {
        val function: (Int) -> Int = { it + 13 }
        val f: (Int) -> Int = { it + 42 }
        val g: (Int) -> Int = { it + 37 }
        val reader = Reader(function)
        val fmapf: (Reader<Int, Int>) -> Reader<Int, Int> = { it.fmap(f) }
        val fmapg: (Reader<Int, Int>) -> Reader<Int, Int> = { it.fmap(g) }
        assert(reader.fmap(compose(f, g))(13)).isEqualTo(compose(fmapf, fmapg)(reader)(13))
    }

}

