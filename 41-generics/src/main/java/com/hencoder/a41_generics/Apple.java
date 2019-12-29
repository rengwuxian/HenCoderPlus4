package com.hencoder.a41_generics;

import java.util.List;

public class Apple implements Fruit {
  @Override
  public float weight() {
    return 2;
  }

  public void addMeToList(List<? super Apple> list) {
    list.add(this);
  }
}
