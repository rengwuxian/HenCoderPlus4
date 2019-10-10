package com.hencoder.a07_retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import retrofit2.Call;

/*public class ProxiedGitHubService implements GitHubService {
  InvocationHandler invocationHandler = new InvocationHandler() {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      // If the method is a method from Object then defer to normal invocation.
      if (method.getDeclaringClass() == Object.class) {
        return method.invoke(this, args);
      }
      if (platform.isDefaultMethod(method)) {
        return platform.invokeDefaultMethod(method, service, proxy, args);
      }
      return loadServiceMethod(method).invoke(args != null ? args : emptyArgs);
    }
  };

  @Override
  public Call<List<Repo>> listRepos(String user) {
    Method method = GitHubService.class.getDeclaredMethod("listRepos", String.class);
    return invocationHandler.invoke(this, method, user);
  }
}*/
