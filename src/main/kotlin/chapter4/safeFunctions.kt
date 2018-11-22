package chapter4

import kotlin.math.sqrt

fun safeReciprocal(n: Double): Maybe<Double> = if (n != 0.0) Maybe.Just(1.0 / n) else Maybe.Nothing()

fun safeRoot(n: Double): Maybe<Double> = if (n >= 0) Maybe.Just(sqrt(n)) else Maybe.Nothing()

val safeRootReciprocal: (Double) -> Maybe<Double> = Maybe.compose(::safeRoot, ::safeReciprocal)