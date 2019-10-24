package com.hencoder.a08_okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // https://api.github.com/users/rengwuxian/repos
    // GET /users HTTP/x.x

    CertificatePinner certificatePinner = new CertificatePinner.Builder()
        .add("api.github.com", "sha256/ORH27mxcLwxnNpR7e0i6pdDPWLXdpeWgr5bEfFVbxW8=")
        .build();
    OkHttpClient client = new OkHttpClient.Builder()
        .certificatePinner(certificatePinner)
        .build();
    final Request request = new Request.Builder()
        .url("https://api.github.com/users/rengwuxian/repos")
        .build();
    client.newCall(request)
        .enqueue(new Callback() {
          @Override
          public void onFailure(@NotNull Call call, @NotNull IOException e) {
            e.printStackTrace();
          }

          @Override
          public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            System.out.println("Response status code: " + response.code());
          }
        });
  }
}
