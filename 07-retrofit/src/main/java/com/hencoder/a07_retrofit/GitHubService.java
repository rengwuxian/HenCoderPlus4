package com.hencoder.a07_retrofit;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);

  @GET("users/{user}/repos")
  Single<List<Repo>> listReposRx(@Path("user") String user);
}