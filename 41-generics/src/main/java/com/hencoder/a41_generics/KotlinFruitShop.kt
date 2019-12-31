package com.hencoder.a41_generics

class KotlinFruitShop<in T : Fruit> {
//  fun getResult() : T {}
  fun processFruit(fruit: T){}
}