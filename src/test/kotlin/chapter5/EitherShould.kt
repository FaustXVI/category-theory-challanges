package chapter5

import assertk.assert
import assertk.assertions.isEqualTo
import kotlin.test.Test


class EitherShould {

    @Test
    fun containsEitherType1OrType2() {
        val left: Either<Int, String> = Either.Left(2)
        val right: Either<Int, String> = Either.Right("value")
        assert((left as Either.Left).v).isEqualTo(2)
        assert((right as Either.Right).v).isEqualTo("value")
    }

}

