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
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

    }

    companion object {
        fun <T> identity(value: T): Maybe<T> = Just(value)
    }
}