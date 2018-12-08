package chapter10

import chapter4.Maybe
import kotlin.test.Test
import assertk.assert
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import chapter7.Reader
import java.time.LocalTime
import kotlin.random.Random
import kotlin.random.nextInt

class NaturalTransformationShould {

    @Test
    fun collapseToEmptyListIsANaturalTransformation() {
        val transformation: (Maybe<Int>) -> List<Int> = { emptyList() }
        val f: (Int) -> Int = { it * 2 }
        val maybe: Maybe.Nothing<Int> = Maybe.Nothing()
        assert(transformation(maybe).map(f)).isEqualTo(transformation(maybe.fmap(f)))
        val maybe1 = Maybe.Just(2)
        assert(transformation(maybe1).map(f)).isEqualTo(transformation(maybe1.fmap(f)))
    }

    @Test
    fun transformationToSingletonListIsANaturalTransformation() {
        val transformation: (Maybe<Int>) -> List<Int> = { it.toList() }
        val f: (Int) -> Int = { it * 2 }
        assert(transformation(Maybe.Just(2))).contains(2)
        val maybe: Maybe.Nothing<Int> = Maybe.Nothing()
        assert(transformation(maybe).map(f)).isEqualTo(transformation(maybe.fmap(f)))
        val maybe1 = Maybe.Just(2)
        assert(transformation(maybe1).map(f)).isEqualTo(transformation(maybe1.fmap(f)))
    }

    @Test
    fun transformationIsPolymorphic() {
        val f: (String) -> Int = { it.toInt() }
        assert(Maybe.Nothing<String>().toList().map(f)).isEqualTo(Maybe.Nothing<String>().fmap(f).toList())
        assert(Maybe.Just("2").toList().map(f)).isEqualTo(Maybe.Just("2").fmap(f).toList())
    }


    @Test
    fun transformation1ofReaderUnit() {
        val transformation: (Reader<Unit, Int>) -> List<Int> = { emptyList() }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Unit, Int> = Reader { 42 }
        assert(transformation(reader).map(f)).isEqualTo(transformation(reader.fmap(f)))
    }

    @Test
    fun transformation2ofReaderUnit() {
        val transformation: (Reader<Unit, Int>) -> List<Int> = { listOf(it(Unit)) }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Unit, Int> = Reader { 42 }
        assert(transformation(reader).map(f)).isEqualTo(transformation(reader.fmap(f)))
    }

    @Test
    fun transformation3ofReaderUnit() {
        val n = Random(LocalTime.now().nano).nextInt(2..100)
        val transformation: (Reader<Unit, Int>) -> List<Int> = { r -> List(n) { r(Unit) } }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Unit, Int> = Reader { 42 }
        assert(transformation(reader).map(f)).isEqualTo(transformation(reader.fmap(f)))
    }

    @Test
    fun transformation1ofReaderBool() {
        val transformation: (Reader<Boolean, Int>) -> Maybe<Int> = { Maybe.Nothing() }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Boolean, Int> = Reader { if (it) 42 else 0 }
        assert(transformation(reader).fmap(f)).isEqualTo(transformation(reader.fmap(f)))
    }

    @Test
    fun transformation2ofReaderBool() {
        val transformation: (Reader<Boolean, Int>) -> Maybe<Int> = { Maybe.Just(it(true)) }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Boolean, Int> = Reader { if (it) 42 else 0 }
        assert(transformation(reader).fmap(f)).isEqualTo(transformation(reader.fmap(f)))
    }

    @Test
    fun transformation3ofReaderBool() {
        val transformation: (Reader<Boolean, Int>) -> Maybe<Int> = { Maybe.Just(it(false)) }
        val f: (Int) -> Int = { it * 2 }
        val reader: Reader<Boolean, Int> = Reader { if (it) 42 else 0 }
        assert(transformation(reader).fmap(f)).isEqualTo(transformation(reader.fmap(f)))
    }

}
