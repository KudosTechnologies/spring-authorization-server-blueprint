package ro.kudostech;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
  private static final String AUTHORIZATION_URL = "http://localhost:8080/oauth2";
  private static final String CLIENT_ID = "client";
  private static final String CLIENT_SECRET = "secret";

  @SneakyThrows
  public static void main(String[] args) {
    String accessToken = retrieveAccessToken();
    System.out.println("Access token: " + accessToken);
  }

  private static String retrieveAccessToken()
      throws IOException, ExecutionException, InterruptedException {
    return new ServiceBuilder(CLIENT_ID)
        .apiKey(CLIENT_ID)
            .apiSecret(CLIENT_SECRET)
        .build(getDefaultApi20())
        .getAccessTokenPasswordGrant("user", "password")
        .getAccessToken();
  }

  private static DefaultApi20 getDefaultApi20() {
    return new DefaultApi20() {
      @Override
      public String getAccessTokenEndpoint() {
//        return AUTHORIZATION_URL + "/token";
        return "http://localhost:8080/oauth2/token";
      }

      @Override
      protected String getAuthorizationBaseUrl() {
        return "http://localhost:8080/oauth2/token";
      }
    };
  }
}
