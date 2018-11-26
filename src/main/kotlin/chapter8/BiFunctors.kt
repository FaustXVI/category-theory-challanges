package chapter8

import chapter1.identity
import chapter7.Functor

interface Bifunctor<A, B> {
    fun <C, D> bimap(f: (A) -> C, g: (B) -> D): Bifunctor<C, D>
    fun <C> first(f: (A) -> C): Bifunctor<C, B> = bimap(f, ::identity)
    fun <D> second(g: (B) -> D): Bifunctor<A, D> = bimap(::identity, g)
}

data class MyPair<A, B>(val first: A, val second: B) : Bifunctor<A, B>, Functor<B> {
    override fun <R> fmap(f: (B) -> R): MyPair<A, R> = second(f) as MyPair<A, R>

    override fun <C, D> bimap(f: (A) -> C, g: (B) -> D): MyPair<C, D> = MyPair(f(first), g(second))
}