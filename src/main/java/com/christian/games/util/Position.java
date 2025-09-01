package com.christian.games.util;

import java.util.Objects;

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
  private boolean enabled;

  public Position(final int x, final int y) {
    this(x, y, false, true);
  }

  public Position(int x, int y, boolean locked) {
    this(x, y, locked, true);
  }

  public Position(int x, int y, boolean locked, boolean enabled) {
    this.x = x;
    this.y = y;
    this.locked = locked;
    this.enabled = enabled;
  }

  /*-- Methods --*/

  public static Position combine(final Position... positions) {
    Position result = new Position(0, 0);
    for (Position position : positions) {
      result.translate(position);
    }
    return result;
  }

  public void translate(final Position position) {
    if (!locked) {
      x += position.x;
      y += position.y;
    }
  }

  public void set(final Position position) {
    if (!locked) {
      x = position.x;
      y = position.y;
    }
  }

  public String toChessNotation() {
    return String.format("%c%c",'a' + x, '8' - y);
  }

  public int toId() {
    return x + (y * 8);
  }

  public void lock() {
    locked = true;
  }

  public void unlock() {
    locked = false;
  }

  public void enable() {
    enabled = true;
  }

  public void disable() {
    enabled = false;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Position position)) {
      return false;
    }
    return x == position.x && y == position.y && enabled == position.enabled;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, enabled);
  }

  @Override
  public String toString() {
    return "Position{" +
        "x=" + x +
        ", y=" + y +
        ", enabled=" + enabled +
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

  public boolean isLocked() {
    return locked;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
