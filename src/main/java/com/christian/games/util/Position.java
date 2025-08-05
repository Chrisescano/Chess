package com.christian.games.util;

public class Position {

  public static final Position NORTH = new Position(0, -1, true);
  public static final Position EAST = new Position(1, 0, true);
  public static final Position SOUTH = new Position(0, 1, true);
  public static final Position WEST = new Position(-1, 0, true);
  public static final Position NORTH_EAST = new Position(1, -1, true);
  public static final Position NORTH_WEST = new Position(-1, -1, true);
  public static final Position SOUTH_EAST = new Position(1, 1, true);
  public static final Position SOUTH_WEST = new Position(-1, 1, true);

  private int x;
  private int y;
  private boolean locked;
  private boolean enabled = true;

  public Position(final int x, final int y) {
    this(x, y, false);
  }

  public Position(int x, int y, boolean locked) {
    this.x = x;
    this.y = y;
    this.locked = locked;
  }

  /*-- Methods --*/

  public static Position combine(final Position... positions) {
    Position result = new Position(0, 0);
    for (Position position : positions) {
      result.add(position);
    }
    return result;
  }

  public void add(final Position position) {
    if (!locked) {
      x += position.x;
      y += position.y;
    }
  }

  public int toId() {
    return x + (y * 8);
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
    if (!locked) {
      this.x = x;
    }
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    if (!locked) {
      this.y = y;
    }
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
