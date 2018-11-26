package chapter7

import chapter1.compose

interface Functor<T> {
    fun <R> fmap(f: (T) -> R): Functor<R>
}

class Reader<R, A>(val function: (R) -> A) : Functor<A> {

    override fun <B> fmap(f: (A) -> B): Reader<R, B> = Reader(compose(function, f))

    operator fun invoke(value: R): A = function(value)

}