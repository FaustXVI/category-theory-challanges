package chapter4

fun makeTotal(f: (Int) -> Int): (Int) -> Maybe<Int> = { x ->
    try {

        Maybe.Just(f(x))
    } catch (e: Exception) {
        Maybe.Nothing()
    }
}

sealed class Maybe<T> {
    abstract val value: T

    data class Just<T>(override val value: T) : Maybe<T>()
    class Nothing<T> : Maybe<T>() {
        override val value: T
            get() = TODO("No value")

        override fun equals(other: Any?): Boolean = other is Nothing<*>

        override fun hashCode(): Int = 0

    }

    companion object {
        fun <T> identity(value: T): Maybe<T> = Just(value)
        fun <A, B, C> compose(f: (B) -> Maybe<C>, g: (A) -> Maybe<B>): (A) -> Maybe<C> = { x ->
            val gResult = g(x)
            when (gResult) {
                is Nothing -> Nothing()
                else -> f(gResult.value)
            }
        }
    }
}