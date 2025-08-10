package com.christian.games.util;

public abstract class BaseInitializer implements Initializable {

  protected boolean initialized;

  /*-- Abstract Methods --*/

  protected abstract void doInit();

  /*-- Methods --*/

  @Override
  public void init() {
    if (initialized) {
      return;
    }
    doInit();
    initialized = true;
  }
}
