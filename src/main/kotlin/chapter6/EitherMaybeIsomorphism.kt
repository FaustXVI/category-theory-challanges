package chapter6

import chapter4.Maybe
import chapter5.Either

fun <T> eitherToMaybe(either: Either<Unit, T>): Maybe<T> = when (either) {
    is Either.Right -> Maybe.Just(either.v)
    else -> Maybe.Nothing()
}

fun <T> maybeToEither(maybe: Maybe<T>): Either<Unit, T> = when (maybe) {
    is Maybe.Just -> Either.Right(maybe.value)
    else -> Either.Left(Unit)
}