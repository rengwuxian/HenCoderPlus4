package com.hencoder.a41_generics;

public class Eater<T extends Food> {
  void eat(T food) {
    food.eaten();
  }
}