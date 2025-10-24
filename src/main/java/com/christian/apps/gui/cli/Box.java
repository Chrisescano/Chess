package com.christian.apps.gui.cli;

import static com.christian.apps.gui.GuiUtility.isBetween;

import java.util.Objects;

public class Box {

  private int x;
  private int y;
  private int width;
  private int height;
  private int topThickness;
  private int bottomThickness;
  private int leftThickness;
  private int rightThickness;

  public Box() {

  }

  public Box(final Box box) {
    x = box.x;
    y = box.y;
    width = box.width;
    height = box.height;
    topThickness = box.topThickness;
    bottomThickness = box.bottomThickness;
    leftThickness = box.leftThickness;
    rightThickness = box.rightThickness;
  }

  /*-- Methods --*/

  public int getEndX() {
    return width + x;
  }

  public int getEndY() {
    return height + y;
  }

  public int getInnerStartX() {
    return x + leftThickness;
  }

  public int getInnerStartY() {
    return y + topThickness;
  }

  public int getInnerEndX() {
    return getEndX() - rightThickness;
  }

  public int getInnerEndY() {
    return getEndY() - bottomThickness;
  }

  public int getInnerWidth() {
    return width - leftThickness - rightThickness;
  }

  public int getInnerHeight() {
    return height - topThickness - bottomThickness;
  }

  public Region getRegion(final int x, final int y) {
    final boolean insideWidth = isBetween(this.x, getEndX(), x);
    final boolean insideHeight = isBetween(this.y, getEndX(), y);
    final boolean insideTop = insideWidth && isBetween(this.y, getInnerStartY(), y);
    final boolean insideBottom = insideWidth && isBetween(getInnerEndY(), getEndY(), y);
    final boolean insideLeft = isBetween(this.x, getInnerStartX(), x) && insideHeight;
    final boolean insideRight = isBetween(getInnerEndX(), getEndX(), x) && insideHeight;

    if (insideTop && insideLeft) {
      return Region.TOP_LEFT;
    } else if (insideTop && insideRight) {
      return Region.TOP_RIGHT;
    } else if (insideBottom && insideLeft) {
      return Region.BOTTOM_LEFT;
    } else if (insideBottom && insideRight) {
      return Region.BOTTOM_RIGHT;
    } else if (insideTop) {
      return Region.TOP;
    } else if (insideLeft) {
      return Region.LEFT;
    } else if (insideRight) {
      return Region.RIGHT;
    } else if (insideBottom) {
      return Region.BOTTOM;
    } else {
      return insideWidth && insideHeight ? Region.CENTER : null;
    }
  }

  @Override
  public String toString() {
    return "Box{" +
        "x=" + x +
        ", y=" + y +
        ", width=" + width +
        ", height=" + height +
        ", topThickness=" + topThickness +
        ", bottomThickness=" + bottomThickness +
        ", leftThickness=" + leftThickness +
        ", rightThickness=" + rightThickness +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Box box)) {
      return false;
    }
    return x == box.x && y == box.y && width == box.width && height == box.height
        && topThickness == box.topThickness && bottomThickness == box.bottomThickness
        && leftThickness == box.leftThickness && rightThickness == box.rightThickness;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, width, height, topThickness, bottomThickness, leftThickness,
        rightThickness);
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

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getTopThickness() {
    return topThickness;
  }

  public void setTopThickness(int topThickness) {
    this.topThickness = topThickness;
  }

  public int getBottomThickness() {
    return bottomThickness;
  }

  public void setBottomThickness(int bottomThickness) {
    this.bottomThickness = bottomThickness;
  }

  public int getLeftThickness() {
    return leftThickness;
  }

  public void setLeftThickness(int leftThickness) {
    this.leftThickness = leftThickness;
  }

  public int getRightThickness() {
    return rightThickness;
  }

  public void setRightThickness(int rightThickness) {
    this.rightThickness = rightThickness;
  }
}
