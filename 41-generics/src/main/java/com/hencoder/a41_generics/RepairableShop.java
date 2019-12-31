package com.hencoder.a41_generics;

public interface RepairableShop<T> extends Shop<T> {
  void repair(T item);
}
