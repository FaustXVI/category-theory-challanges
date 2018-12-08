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
        val transformation: (Maybe<Int>) -> List<Int> = { it.toList() }
        val f: (Int) -> Int = { it * 2 }
        assert(transformation(Maybe.Just(2))).contains(2)
        checkNaturalityCondition(transformation, Maybe.Nothing(), f)
        checkNaturalityCondition(transformation, Maybe.Just(2), f)
    }

    @Test
    fun transformationIsPolymorphic() {
        val f: (String) -> Int = { it.toInt() }
        assert(Maybe.Nothing<String>().toList().map(f)).isEqualTo(Maybe.Nothing<String>().map(f).toList())
        assert(Maybe.Just("2").toList().map(f)).isEqualTo(Maybe.Just("2").map(f).toList())
    }

    private fun checkNaturalityCondition(transformation: (Maybe<Int>) -> List<Int>, maybe: Maybe<Int>, f: (Int) -> Int) {
        assert(transformation(maybe).map(f)).isEqualTo(transformation(maybe.map(f)))
    }

}
