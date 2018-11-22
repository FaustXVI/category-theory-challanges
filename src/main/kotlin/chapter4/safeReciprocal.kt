package chapter4

fun safeReciprocal(n: Double): Maybe<Double> {
    return Maybe.Just(1.0 / n)
}