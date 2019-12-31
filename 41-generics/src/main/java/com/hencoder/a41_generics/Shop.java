package com.hencoder.a41_generics;

import java.io.Serializable;

public interface Shop<T> {
  T buy();
  float refund(T item);

  <E> E refurbish(E item, float money);

  <P extends Runnable & Serializable> void someMethod(P param); // Runnable; Serializable

  // Shop<Apple> shop = ...;
  // Tv tv = ...;
  // Tv newTv = shop.refurbish(tv, 100);
}