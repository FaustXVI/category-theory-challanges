package chapter9

import chapter4.Maybe

fun <T> maybeToList(maybe: Maybe<T>): List<T> = maybe.toList()

fun <T> Maybe<T>.toList(): List<T> = when (this) {
    is Maybe.Nothing -> emptyList()
    is Maybe.Just -> listOf(value)
}