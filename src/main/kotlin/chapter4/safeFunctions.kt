package chapter4

fun safeReciprocal(n: Double): Maybe<Double> = if (n != 0.0) Maybe.Just(1.0 / n) else Maybe.Nothing()