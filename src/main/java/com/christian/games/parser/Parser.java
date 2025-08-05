package com.christian.games.parser;

public interface Parser<T,R> {
  R parse(final T input);
  T unparse(final R input);
}
