package chapter9

import chapter4.Maybe
import kotlin.test.Test
import assertk.assert
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import chapter7.Reader
import java.time.LocalTime
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class NaturalTransformationShould {

    @Test
    fun collapseToEmptyListIsANaturalTransformation() {
        val transformation: (Maybe<Int>) -> List<Int> = { emptyList() }
        val f: (Int) -> Int = { it * 2 }
        val maybe: Maybe.Nothing<Int> = Maybe.Nothing()
        assert(transformation(maybe).map(f)).isEqualTo(transformation(maybe.map(f)))
        val maybe1 = Maybe.Just(2)
        assert(transformation(maybe1).map(f)).isEqualTo(transformation(maybe1.map(f)))
    }

    @Test
    fun transformationToSingletonListIsANaturalTransformation() {
        val transformation: (Maybe<Int>) -> List<Int> = { it.toList() }
        val f: (Int) -> Int = { it * 2 }
        assert(transformation(Maybe.Just(2))).contains(2)
        val maybe: Maybe.Nothing<Int> = Maybe.Nothing()
        assert(transformation(maybe).map(f)).isEqualTo(transformation(maybe.map(f)))
        val maybe1 = Maybe.Just(2)
        assert(transformation(maybe1).map(f)).isEqualTo(transformation(maybe1.map(f)))
    }

    @Test
    fun transformationIsPolymorphic() {
        val f: (String) -> Int = { it.toInt() }
        assert(Maybe.Nothing<String>().toList().map(f)).isEqualTo(Maybe.Nothing<String>().map(f).toList())
        assert(Maybe.Just("2").toList().map(f)).isEqualTo(Maybe.Just("2").map(f).toList())
    }


    @Test
    fun transformation1ofReader() {
        val transformation: (Reader<Unit, Int>) -> List<Int> = { emptyList() }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Unit, Int> = Reader { 42 }
        assert(transformation(reader).map(f)).isEqualTo(transformation(reader.fmap(f)))
    }

    @Test
    fun transformation2ofReader() {
        val transformation: (Reader<Unit, Int>) -> List<Int> = { listOf(it(Unit)) }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Unit, Int> = Reader { 42 }
        assert(transformation(reader).map(f)).isEqualTo(transformation(reader.fmap(f)))
    }

    @Test
    fun transformation3ofReader() {
        val n = Random(LocalTime.now().nano).nextInt(2..100)
        val transformation: (Reader<Unit, Int>) -> List<Int> = { r -> List(n) { r(Unit) } }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Unit, Int> = Reader { 42 }
        assert(transformation(reader).map(f)).isEqualTo(transformation(reader.fmap(f)))
    }

}
