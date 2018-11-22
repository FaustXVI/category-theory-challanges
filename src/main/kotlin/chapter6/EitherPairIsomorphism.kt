package chapter6

import chapter5.Either

fun <T> eitherToPair(sum: Either<T, T>): Pair<Boolean, T> = when (sum) {
    is Either.Left -> Pair(false, sum.v)
    else -> TODO()
}