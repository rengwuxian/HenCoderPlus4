package com.hencoder.a37_pluginnable;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

public class ProxyActivity extends AppCompatActivity {
  Object realActivity;

//  realActivity = dexClassLoader.loadClass(???);

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    realActivity.onCreate(savedInstanceState);
  }

  @Override
  protected void onStart() {
    super.onStart();
//    realActivity.onStart();
  }

  @Override
  public Resources getResources() {
    return new Resources(getAssets(), super.getResources().getDisplayMetrics(), super.getResources().getConfiguration());
  }

  private AssetManager createAssetManager(String dexPath) {
    try {
      AssetManager assetManager = AssetManager.class.newInstance();
      Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
      addAssetPath.invoke(assetManager, dexPath);
      return assetManager;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /*private Resources createResources(AssetManager assetManager) {
    Resources superRes = mContext.getResources();
    Resources resources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
    return resources;
  }*/
}
