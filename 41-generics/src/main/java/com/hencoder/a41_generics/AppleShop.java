package com.hencoder.a41_generics;

import java.io.Serializable;
import java.util.List;

public class AppleShop<T> implements Shop<Apple> {
//  T instance = new T();
//  T[] instances = new T[10];

  @Override
  public Apple buy() {
    return null;
  }

  @Override
  public float refund(Apple item) {
    return 0;
  }

  @Override
  public <E> E refurbish(E item, float money) {
    return null;
  }

  @Override
  public <E extends Runnable & Serializable> void someMethod(E param) {

  }

  public <E> void addToList(E item, List<E> list) {
    list.add(item);
  }

  // Apple, List<Apple>
  // Apple, List<Banana>
}
