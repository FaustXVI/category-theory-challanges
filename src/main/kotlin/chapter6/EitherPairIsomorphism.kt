package chapter6

import chapter5.Either

fun <T> eitherToPair(sum: Either<T, T>): Pair<Boolean, T> = when (sum) {
    is Either.Left -> Pair(false, sum.v)
    is Either.Right -> Pair(true, sum.v)
}

fun pairToEither(product: Pair<Boolean, Int>): Either<Int, Int> = if (product.first) {
    Either.Right(product.second)
} else {
    Either.Left(product.second)
}