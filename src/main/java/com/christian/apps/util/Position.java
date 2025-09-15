package com.christian.apps.util;

public class Position {

  public static final Position NORTH = new Position(0, -1);
  public static final Position EAST = new Position(1, 0);
  public static final Position SOUTH = new Position(0, 1);
  public static final Position WEST = new Position(-1, 0);
  public static final Position NORTH_EAST = new Position(1, -1);
  public static final Position NORTH_WEST = new Position(-1, -1);
  public static final Position SOUTH_EAST = new Position(1, 1);
  public static final Position SOUTH_WEST = new Position(-1, 1);

  private int x;
  private int y;

  public Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  /*-- Methods --*/

  public static Position difference(final Position posA, final Position posB) {
    return new Position(posB.x - posA.x, posB.y - posA.y);
  }

  public void setTo(final Position position) {
    x = position.x;
    y = position.y;
  }

  public void translate(final Position position) {
    x += position.x;
    y += position.y;
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
