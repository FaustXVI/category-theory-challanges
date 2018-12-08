package chapter9

import chapter4.Maybe
import kotlin.test.Test
import assertk.assert
import assertk.assertions.isEqualTo

class NaturalTransformationShould {

    @Test
    fun transformsAMaybeIntoAList() {
        val nothing: Maybe<Int> = Maybe.Nothing()
        val two: Maybe<Int> = Maybe.Just(2)
        val f: (Int) -> Int = { it * 2 }
        val transformation = ::maybeToList
        assert(transformation(nothing).map(f)).isEqualTo(transformation(nothing.map(f)))
        assert(transformation(two).map(f)).isEqualTo(transformation(two.map(f)))
    }

}

fun maybeToList(maybe: Maybe<Int>): List<Int> = emptyList()