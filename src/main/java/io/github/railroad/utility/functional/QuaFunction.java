package io.github.railroad.utility.functional;

@FunctionalInterface
public interface QuaFunction<A, B, C, D, R> {
    R apply(A a, B b, C c, D d);
}
