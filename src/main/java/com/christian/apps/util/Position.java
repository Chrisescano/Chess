package com.christian.apps.util;

public class Position {

  private int x;
  private int y;

  public Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  /*-- Methods --*/

  public void setTo(final Position position) {
    x = position.x;
    y = position.y;
  }

  @Override
  public String toString() {
    return "Position{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }

  /*-- Getters/Setters --*/

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
