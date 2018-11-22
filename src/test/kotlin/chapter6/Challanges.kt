package chapter6

import assertk.assert
import assertk.assertions.isEqualTo
import chapter4.Maybe
import chapter5.Either
import kotlin.test.Test

class Challanges {

    @Test
    fun isomorphism() {
        val right: Either<Unit, Int> = Either.Right(42)
        val left: Either<Unit, Int> = Either.Left(Unit)
        val maybe: Maybe<Int> = Maybe.Just(42)
        val nothing: Maybe<Int> = Maybe.Nothing()

        assert(eitherToMaybe(maybeToEither(maybe))).isEqualTo(maybe)
        assert(maybeToEither(eitherToMaybe(right))).isEqualTo(right)
        assert(eitherToMaybe(maybeToEither(nothing))).isEqualTo(nothing)
        assert(maybeToEither(eitherToMaybe(left))).isEqualTo(left)
    }

    @Test
    fun question5() {
        val sum31: Either<Int, Int> = Either.Left(31)
        val product31: Pair<Boolean, Int> = Pair(false, 31)
        val sum42: Either<Int, Int> = Either.Right(42)
        val product42: Pair<Boolean, Int> = Pair(true, 42)

        assert(eitherToPair(sum31)).isEqualTo(product31)
        assert(pairToEither(product31)).isEqualTo(sum31)
        assert(eitherToPair(sum42)).isEqualTo(product42)
        assert(pairToEither(product42)).isEqualTo(sum42)
    }

}

