package com.christian.games.util;

public class BaseInitializer implements Initializable {

  protected boolean initialized;

  @Override
  public void init() {
    initialized = true;
  }
}
