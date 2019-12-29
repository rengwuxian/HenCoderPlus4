package com.hencoder.a41_generics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    List<? extends Fruit> fruits = new ArrayList<Apple>(); // 不能用 add(item)
//    List<? super Apple> apples = new ArrayList<Fruit>(); // 不能用 get()

    /*List<Fruit> fruits = new ArrayList<>();
    fruits.add(new Apple());
    fruits.add(new Banana());
    float fruitsWeight = totalWeight(fruits);*/

    /*Fruit[] fruitsArray = new Apple[10];
    fruitsArray[0] = new Apple();
    fruitsArray[1] = new Banana();
    System.out.println("Fruits Array 插入了香蕉！");*/

    /*List<? extends Fruit> fruits = new ArrayList<Apple>();
    fruits.add(null);
    Apple apple = (Apple) fruits.get(0);
    System.out.println("Fruits List 插入了香蕉！" + apple.weight());

    Eater<Human> monster = new Eater<Human>();
    monster.eat(new Human());
    Eater<Animal> beast = new Eater<Animal>();
    beast.eat(new Animal());
    beast.eat(new Human());
    Eater<? extends Animal> animalEater = new Eater<Human>();
    animalEater.eat(new Panda());*/

//    List<? super Apple> apples = new ArrayList<Fruit>();

    Apple apple = new Apple();
    List<Apple> apples = new ArrayList<>();
    List<Fruit> fruits = new ArrayList<>();
    apple.addMeToList(apples);
    apple.addMeToList(fruits);
  }

  public float totalWeight(List<? extends Fruit> fruits) {
    float weight = 0;
    for (Fruit fruit : fruits) {
      weight += fruit.weight();
    }
    return weight;
  }
}
