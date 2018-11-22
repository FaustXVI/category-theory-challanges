package chapter5

sealed class Either<T, U> {
    data class Left<T, U>(val v: T) : Either<T, U>()

    data class Right<T, U>(val v: U) : Either<T, U>()

}