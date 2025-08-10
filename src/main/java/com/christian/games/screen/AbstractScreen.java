package com.christian.games.screen;

import com.christian.games.util.BaseInitializer;

public class AbstractScreen extends BaseInitializer implements Screen {

  @Override
  public String getUserResponse(String prompt) {
    return "";
  }

  @Override
  public void pushNotification(String message) {

  }

  @Override
  protected void doInit() {

  }
}
