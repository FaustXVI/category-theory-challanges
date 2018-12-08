package chapter9

import chapter4.Maybe
import kotlin.test.Test
import assertk.assert
import assertk.assertions.isEqualTo

class NaturalTransformationShould {

    @Test
    fun collapseToEmptyListIsANaturalTransformation() {
        val transformation: (Maybe<Int>) -> List<Int> = { emptyList() }
        val f: (Int) -> Int = { it * 2 }
        checkNaturalityCondition(transformation, Maybe.Nothing(), f)
        checkNaturalityCondition(transformation, Maybe.Just(2), f)
    }

    private fun checkNaturalityCondition(transformation: (Maybe<Int>) -> List<Int>, nothing: Maybe<Int>, f: (Int) -> Int) {
        assert(transformation(nothing).map(f)).isEqualTo(transformation(nothing.map(f)))
    }

}