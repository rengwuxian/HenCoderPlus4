package com.hencoder.a41_generics;

public interface Shop<T> {
  T buy();
  float refund(T item);
}
