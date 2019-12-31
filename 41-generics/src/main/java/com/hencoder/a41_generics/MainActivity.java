package com.hencoder.a41_generics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//  List<? super Fruit> list4 = ...;

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
    List<Apple> apples = new ArrayList<Apple>(){};
    List<Fruit> fruits = new ArrayList<Fruit>(){};
    apple.addMeToList(apples);
    apple.addMeToList(fruits);

    /*Shop<Apple> appleShop = new Shop<Apple>() {
      @Override
      public Apple buy() {
        return null;
      }

      @Override
      public float refund(Apple item) {
        return 0;
      }

      @Override
      public <E> E refurbish(E item, float money) {
        return null;
      }
    };
    appleShop.buy();
    appleShop.<String>refurbish("haha", 100);
    Banana banana = new Banana();
    Banana newBanana = appleShop.refund(banana);
    List<? extends Fruit> list0 = ...;
    List<? super Fruit> list1 = ...;
    List<?> list2 = ...;
    List<Shop<Apple>> listA;
    new ArrayList<>();

    Object list = ...;
    if (list instanceof List<String>) {

    }
    Field field = ...;
    field.getType();
    field.getGenericType();
    Method method = ...;
    method.getGenericReturnType()

    Object a = List<String>.class;

    TypeToken<List<String>> listStringTypeToken = new TypeToken<List<String>>(){};

    Object[] objects = new Apple[10];
    List<Object> objectsList = new ArrayList<Apple>();*/

    Shop<?>[] appleShops = new Shop<?>[10];
    List<Shop<Apple>> appleShopList = new ArrayList<Shop<Apple>>();

    List<? extends Fruit> fruitList = new ArrayList<Apple>(); // List<out Fruit>
    List<? super Apple> appleList = new ArrayList<Fruit>(); // List<in Fruit>
  }

  /*public List<? extends Fruit> getFruitList() {

  }*/

  public float totalWeight(List<? extends Fruit> fruits) {
    float weight = 0;
    for (Fruit fruit : fruits) {
      weight += fruit.weight();
    }
    return weight;
  }

  /*public final class Integer implements Comparable<Integer> {

  }

  public final class Integer {
    public int compareTo(Integer o);
  }*/
}
