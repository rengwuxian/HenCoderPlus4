package com.hencoder.a41_generics;

public interface SimShop<T, C extends Sim> extends Shop<T> {
  C getSim(String name, String id);
}