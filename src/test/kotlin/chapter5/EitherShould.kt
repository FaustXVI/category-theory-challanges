package chapter5

import assertk.assert
import assertk.assertions.isEqualTo
import kotlin.test.Test


class EitherShould {

    @Test
    fun containsEitherType1OrType2() {
        val left: Either<Int, String> = Either.Left(2)
        assert(left.asLeft()).isEqualTo(2)
    }

}

sealed class Either<T, U> {
    abstract fun asLeft(): T

    data class Left<T, U>(val v: T) : Either<T, U>() {
        override fun asLeft(): T = v
    }

}
