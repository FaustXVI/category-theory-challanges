package chapter9

import chapter4.Maybe

fun <T> maybeToList(maybe: Maybe<T>): List<T> = when (maybe) {
    is Maybe.Nothing -> emptyList()
    is Maybe.Just -> listOf(maybe.value)
}