package com.christian.apps.gui.cli;

import java.util.Objects;

public class Context {

  private final Box marginBox = new Box();
  private final Box borderBox = new Box();
  private final Box paddingBox = new Box();
  private final Style marginStyle = new Style();
  private final Style borderStyle = new Style();
  private final Style paddingStyle = new Style();
  private boolean isStale = true;

  /*-- Methods --*/

  public void calculateBounds(final Buffer2D contentBuffer) {
    if (isStale) {
      calculatePaddingBounds();
      calculateContentBounds(contentBuffer.getBufferWidth(), contentBuffer.getBufferHeight());
      isStale = false;
    }
  }

  public void setBorderSize(final int width, final int height) {
    borderBox.setWidth(width);
    borderBox.setHeight(height);
    isStale = true;
  }

  public void setBorderThickness(final int top, final int bottom, final int left, final int right) {
    borderBox.setTopThickness(top);
    borderBox.setBottomThickness(bottom);
    borderBox.setLeftThickness(left);
    borderBox.setRightThickness(right);
    isStale = true;
  }

  public void setPaddingThickness(final int top, final int bottom, final int left, final int right) {
    paddingBox.setTopThickness(top);
    paddingBox.setBottomThickness(bottom);
    paddingBox.setLeftThickness(left);
    paddingBox.setRightThickness(right);
    isStale = true;
  }

  @Override
  public String toString() {
    return "Context{" +
        "margin=" + marginBox +
        ", border=" + borderBox +
        ", padding=" + paddingBox +
        ", marginStyle=" + marginStyle +
        ", borderStyle=" + borderStyle +
        ", paddingStyle=" + paddingStyle +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Context context)) {
      return false;
    }
    return isStale == context.isStale && Objects.equals(marginBox, context.marginBox)
        && Objects.equals(borderBox, context.borderBox) && Objects.equals(paddingBox,
        context.paddingBox) && Objects.equals(marginStyle, context.marginStyle)
        && Objects.equals(borderStyle, context.borderStyle) && Objects.equals(
        paddingStyle, context.paddingStyle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(marginBox, borderBox, paddingBox, marginStyle, borderStyle, paddingStyle, isStale);
  }

  /*-- Helper Methods --*/

  private void calculatePaddingBounds() {
    paddingBox.setWidth(borderBox.getInnerWidth());
    paddingBox.setHeight(borderBox.getInnerHeight());
  }
  
  private void calculateContentBounds(final int contentWidth, final int contentHeight) {
    if (contentWidth < paddingBox.getWidth()) {
      paddingBox.setLeftThickness(0);
      paddingBox.setRightThickness(paddingBox.getWidth() - contentWidth);
    } else {
      paddingBox.setLeftThickness(0);
      paddingBox.setRightThickness(0);
    }

    if (contentHeight < paddingBox.getHeight()) {
      paddingBox.setTopThickness(0);
      paddingBox.setBottomThickness(paddingBox.getHeight() - contentHeight);
    } else {
      paddingBox.setTopThickness(0);
      paddingBox.setBottomThickness(0);
    }
  }

  /*-- Getters/Setters --*/

  public Box getMarginBox() {
    return new Box(marginBox);
  }

  public Box getBorderBox() {
    return new Box(borderBox);
  }

  public Box getPaddingBox() {
    return new Box(paddingBox);
  }

  public Style getMarginStyle() {
    return new Style(marginStyle);
  }

  public void setMarginStyle(Style marginStyle) {
    this.marginStyle.importFrom(marginStyle);
  }

  public Style getBorderStyle() {
    return new Style(borderStyle);
  }

  public void setBorderStyle(Style borderStyle) {
    this.borderStyle.importFrom(borderStyle);
  }

  public Style getPaddingStyle() {
    return new Style(paddingStyle);
  }

  public void setPaddingStyle(Style paddingStyle) {
    this.paddingStyle.importFrom(paddingStyle);
  }
}
