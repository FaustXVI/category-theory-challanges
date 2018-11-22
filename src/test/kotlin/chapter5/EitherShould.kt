package chapter5

import assertk.assert
import assertk.assertions.isEqualTo
import chapter1.compose
import chapter5.Either.Left
import chapter5.Either.Right
import kotlin.test.Test


class EitherShould {

    @Test
    fun containsEitherType1OrType2() {
        val left: Either<Int, String> = Left(2)
        val right: Either<Int, String> = Right("value")
        assert((left as Left).v).isEqualTo(2)
        assert((right as Right).v).isEqualTo("value")
    }

    @Test
    fun question5() {
        val i: (Int) -> Int = { it }
        val j: (Boolean) -> Int = { if (it) 0 else 1 }
        val left: (Int) -> Either<Int, Boolean> = ::Left
        val right: (Boolean) -> Either<Int, Boolean> = ::Right

        val m: (Either<Int, Boolean>) -> Int = {
            when (it) {
                is Left -> it.v
                is Right -> if (it.v) 0 else 1
            }
        }

        assert(compose(left, m)(42)).isEqualTo(i(42))
        assert(compose(right, m)(false)).isEqualTo(j(false))
    }

    @Test
    fun question6() {
        val i: (Int) -> Int = { it }
        val j: (Boolean) -> Int = { if (it) 0 else 1 }
        val left: (Int) -> Either<Int, Boolean> = ::Left
        val right: (Boolean) -> Either<Int, Boolean> = ::Right

        val m: (Either<Int, Boolean>) -> Int = genericM(i, j)

        assert(compose(left, m)(42)).isEqualTo(i(42))
        assert(compose(right, m)(false)).isEqualTo(j(false))
    }

    @Test
    fun question7() {
        val i: (Int) -> Int = { if (it < 0) it else it + 2 }
        val j: (Boolean) -> Int = { if (it) 0 else 1 }
        val left: (Int) -> Either<Int, Boolean> = ::Left
        val right: (Boolean) -> Either<Int, Boolean> = ::Right

        val m: (Either<Int, Boolean>) -> Int = genericM(i, j)

        assert(compose(left, m)(42)).isEqualTo(i(42))
        assert(compose(right, m)(false)).isEqualTo(j(false))
    }

}

fun genericM(i: (Int) -> Int, j: (Boolean) -> Int): (Either<Int, Boolean>) -> Int = {
    when (it) {
        is Left -> i(it.v)
        is Right -> j(it.v)
    }
}
