package chapter4

import kotlin.math.sqrt

fun safeReciprocal(n: Double): Maybe<Double> = if (n != 0.0) Maybe.Just(1.0 / n) else Maybe.Nothing()

fun safeRoot(n: Double): Maybe<Double> {
    return Maybe.Just(sqrt(n))
}