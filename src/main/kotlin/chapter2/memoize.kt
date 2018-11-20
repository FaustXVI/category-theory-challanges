package chapter2

fun <A, B> memoize(function: (A) -> B): (A) -> B {
    val memoizeCache = mutableMapOf<A, B>()
    return { memoizeCache.computeIfAbsent(it, function) }
}