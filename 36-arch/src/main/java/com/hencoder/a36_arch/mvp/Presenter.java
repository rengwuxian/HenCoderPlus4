package com.hencoder.a36_arch.mvp;

import com.hencoder.a36_arch.data.DataCenter;

public class Presenter {
  private IView iView;

  Presenter(IView iView) {
    this.iView = iView;
  }

  public void load() {
    String[] data = DataCenter.getData();
    iView.showData(data[0], data[1]);
  }

  interface IView {
    void showData(String data0, String data1);
  }
}
