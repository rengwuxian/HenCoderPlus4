package com.hencoder.a41_generics;

public interface EaterShop<F extends Food, T extends Eater<F>> extends Shop<T> {
}
