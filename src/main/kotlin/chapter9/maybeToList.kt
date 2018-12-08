package chapter9

import chapter4.Maybe

fun <T> Maybe<T>.toList(): List<T> = when (this) {
    is Maybe.Nothing -> emptyList()
    is Maybe.Just -> listOf(value)
}