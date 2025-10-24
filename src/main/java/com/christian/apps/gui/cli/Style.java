package com.christian.apps.gui.cli;

import java.util.Objects;

public class Style {

  private char topLeft;
  private char top;
  private char topRight;
  private char left;
  private char center;
  private char right;
  private char bottomLeft;
  private char bottom;
  private char bottomRight;

  public Style() {

  }

  public Style(final Style style) {
    topLeft = style.topLeft;
    top = style.top;
    topRight = style.topRight;
    left = style.left;
    center = style.center;
    right = style.right;
    bottomLeft = style.bottomLeft;
    bottom = style.bottom;
    bottomRight = style.bottomRight;
  }

  /*-- Methods --*/

  public void importFrom(final Style style) {
    if (isSet(style.topLeft)) {
      topLeft = style.topLeft;
    }
    if (isSet(style.top)) {
      top = style.top;
    }
    if (isSet(style.topRight)) {
      topRight = style.topRight;
    }
    if (isSet(style.left)) {
      left = style.left;
    }
    if (isSet(style.center)) {
      center = style.center;
    }
    if (isSet(style.right)) {
      right = style.right;
    }
    if (isSet(style.bottomLeft)) {
      bottomLeft = style.bottomLeft;
    }
    if (isSet(style.bottom)) {
      bottom = style.bottom;
    }
    if (isSet(style.bottomRight)) {
      bottomRight = style.bottomRight;
    }
  }

  public char getChar(final Region region) {
    if (region == null) {
      return Character.MIN_VALUE;
    }

    return switch (region) {
      case TOP_LEFT -> topLeft;
      case TOP -> top;
      case TOP_RIGHT -> topRight;
      case LEFT -> left;
      case CENTER -> center;
      case RIGHT -> right;
      case BOTTOM_LEFT -> bottomLeft;
      case BOTTOM -> bottom;
      case BOTTOM_RIGHT -> bottomRight;
    };
  }

  @Override
  public String toString() {
    return "Style{" +
        "topLeft=" + topLeft +
        ", top=" + top +
        ", topRight=" + topRight +
        ", left=" + left +
        ", center=" + center +
        ", right=" + right +
        ", bottomLeft=" + bottomLeft +
        ", bottom=" + bottom +
        ", bottomRight=" + bottomRight +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Style style)) {
      return false;
    }
    return topLeft == style.topLeft && top == style.top && topRight == style.topRight
        && left == style.left && center == style.center && right == style.right
        && bottomLeft == style.bottomLeft && bottom == style.bottom
        && bottomRight == style.bottomRight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(topLeft, top, topRight, left, center, right, bottomLeft, bottom,
        bottomRight);
  }

  /*-- Helper Methods --*/

  private boolean isSet(final char data) {
    return data != Character.MIN_VALUE;
  }

  /*-- Getters/Setters --*/

  public char getTopLeft() {
    return topLeft;
  }

  public void setTopLeft(char topLeft) {
    this.topLeft = topLeft;
  }

  public char getTop() {
    return top;
  }

  public void setTop(char top) {
    this.top = top;
  }

  public char getTopRight() {
    return topRight;
  }

  public void setTopRight(char topRight) {
    this.topRight = topRight;
  }

  public char getLeft() {
    return left;
  }

  public void setLeft(char left) {
    this.left = left;
  }

  public char getCenter() {
    return center;
  }

  public void setCenter(char center) {
    this.center = center;
  }

  public char getRight() {
    return right;
  }

  public void setRight(char right) {
    this.right = right;
  }

  public char getBottomLeft() {
    return bottomLeft;
  }

  public void setBottomLeft(char bottomLeft) {
    this.bottomLeft = bottomLeft;
  }

  public char getBottom() {
    return bottom;
  }

  public void setBottom(char bottom) {
    this.bottom = bottom;
  }

  public char getBottomRight() {
    return bottomRight;
  }

  public void setBottomRight(char bottomRight) {
    this.bottomRight = bottomRight;
  }
}
