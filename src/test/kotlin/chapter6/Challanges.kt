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
        val sum: Either<Int, Int> = Either.Left(31)
        val product: Pair<Boolean, Int> = Pair(false, 31)

        assert(eitherToPair(sum)).isEqualTo(product)
        assert(pairToEither(product)).isEqualTo(sum)
    }

    private fun pairToEither(product: Pair<Boolean, Int>): Either<Int, Int> = if (product.first) {
        TODO()
    } else {
        Either.Left(product.second)
    }

}

