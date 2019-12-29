package com.hencoder.a41_generics;

import java.util.Arrays;

public class HenCoderList<T> {
  private Object[] instances = new Object[0];

  public T get(int index) {
    return (T) instances[index];
  }

  public void add(T newItem) {
    instances = Arrays.copyOf(instances, instances.length + 1);
    instances[instances.length - 1] = newItem;
  }
}