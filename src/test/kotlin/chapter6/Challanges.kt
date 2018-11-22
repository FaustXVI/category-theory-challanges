package chapter6

import assertk.assertions.isEqualTo
import chapter4.Maybe
import chapter5.Either
import kotlin.test.Test

class Challanges {

    @Test
    fun isomorphism() {
        val right: Either<Unit, Int> = Either.Right(42)
        val maybe: Maybe<Int> = Maybe.Just(42)
        assertk.assert(eitherToMaybe(maybeToEither(maybe))).isEqualTo(maybe)
        assertk.assert(maybeToEither(eitherToMaybe(right))).isEqualTo(right)
    }

    private fun eitherToMaybe(either: Either<Unit, Int>): Maybe<Int> = when(either){
        is Either.Right -> Maybe.Just(either.v)
        else -> TODO()
    }

    private fun maybeToEither(maybe: Maybe<Int>): Either<Unit, Int> = when(maybe){
        is Maybe.Just -> Either.Right(maybe.value)
        else -> TODO()
    }


}