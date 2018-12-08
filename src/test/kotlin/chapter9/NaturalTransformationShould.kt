package chapter9

import chapter4.Maybe
import kotlin.test.Test
import assertk.assert
import assertk.assertions.contains
import assertk.assertions.isEqualTo

class NaturalTransformationShould {

    @Test
    fun collapseToEmptyListIsANaturalTransformation() {
        val transformation: (Maybe<Int>) -> List<Int> = { emptyList() }
        val f: (Int) -> Int = { it * 2 }
        checkNaturalityCondition(transformation, Maybe.Nothing(), f)
        checkNaturalityCondition(transformation, Maybe.Just(2), f)
    }

    @Test
    fun transformationToSingletonListIsANaturalTransformation() {
        val transformation: (Maybe<Int>) -> List<Int> = ::maybeToList
        val f: (Int) -> Int = { it * 2 }
        assert(transformation(Maybe.Just(2))).contains(2)
        checkNaturalityCondition(transformation, Maybe.Nothing(), f)
        checkNaturalityCondition(transformation, Maybe.Just(2), f)
    }

    @Test
    fun transformationIsPolymorphic() {
        val f: (String) -> Int = { it.toInt() }
        assert(maybeToList(Maybe.Nothing<String>()).map(f)).isEqualTo(maybeToList(Maybe.Nothing<String>().map(f)))
        assert(maybeToList(Maybe.Just("2")).map(f)).isEqualTo(maybeToList(Maybe.Just("2").map(f)))
    }

    private fun checkNaturalityCondition(transformation: (Maybe<Int>) -> List<Int>, nothing: Maybe<Int>, f: (Int) -> Int) {
        assert(transformation(nothing).map(f)).isEqualTo(transformation(nothing.map(f)))
    }

}

fun <T> maybeToList(maybe: Maybe<T>): List<T> = when (maybe) {
    is Maybe.Nothing -> emptyList()
    is Maybe.Just -> listOf(maybe.value)
}