package com.hencoder.a41_generics;

public class AppleShop implements Shop<Apple> {
  @Override
  public Apple buy() {
    return null;
  }

  @Override
  public float refund(Apple item) {
    return 0;
  }
}
