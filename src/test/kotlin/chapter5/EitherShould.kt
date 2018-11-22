package chapter5

import assertk.assert
import assertk.assertions.isEqualTo
import chapter1.compose
import kotlin.test.Test


class EitherShould {

    @Test
    fun containsEitherType1OrType2() {
        val left: Either<Int, String> = Either.Left(2)
        val right: Either<Int, String> = Either.Right("value")
        assert((left as Either.Left).v).isEqualTo(2)
        assert((right as Either.Right).v).isEqualTo("value")
    }

    @Test
    fun question5() {
        val i: (Int) -> Int = { it }
        val left: (Int) -> Either<Int, Boolean> = { v: Int -> Either.Left(v) }

        val m: (Either<Int, Boolean>) -> Int = {
            when (it) {
                is Either.Left -> it.v
                else -> 0
            }
        }

        assert(compose(left, m)(42)).isEqualTo(i(42))
    }

}

