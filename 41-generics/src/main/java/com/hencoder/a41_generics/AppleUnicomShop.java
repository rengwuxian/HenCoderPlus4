package com.hencoder.a41_generics;

public class AppleUnicomShop implements SimShop<Apple, ChinaUnicom> {
  @Override
  public ChinaUnicom getSim(String name, String id) {
    return null;
  }

  @Override
  public Apple buy() {
    return null;
  }

  @Override
  public float refund(Apple item) {
    return 0;
  }
}
