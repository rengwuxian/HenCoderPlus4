package com.hencoder.a41_generics;

public interface RepairableShop<E> extends Shop<E> {
  void repair(E item);
}
