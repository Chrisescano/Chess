package com.christian.apps.gui.cli;

import java.util.HashMap;
import java.util.Map;

public abstract class Component {

  private final Context context = new Context();
  private final Map<Box, Buffer2D> bufferCache = new HashMap<>();

  /*-- Abstract Methods --*/

  protected abstract Buffer2D renderContent();

  /*-- Methods --*/

  public Buffer2D render() {
    final Buffer2D content = renderContent();
    context.calculateBounds(content);

    final Box borderBox = context.getBorderBox();
    Buffer2D cachedBuffer = bufferCache.get(borderBox);
    if (cachedBuffer == null) {
      cachedBuffer = Buffer2D.create(borderBox.getWidth(), borderBox.getHeight());
      renderBorder(cachedBuffer);
      bufferCache.put(borderBox, cachedBuffer);
    }

    final Box paddingBox = context.getPaddingBox();
    renderPadding(cachedBuffer);
    renderContent(content, cachedBuffer);
    return cachedBuffer;
  }

  /*-- Helper Methods --*/

  private void renderBorder(final Buffer2D dest) {
    final Box borderBox = context.getBorderBox();
    final Style borderStyle = context.getBorderStyle();
    renderSection(borderBox, borderStyle, dest);
  }

  private void renderPadding(final Buffer2D dest) {
    final Box paddingBox = context.getPaddingBox();
    final Style paddingStyle = context.getPaddingStyle();
    renderSection(paddingBox, paddingStyle, dest);
  }

  private void renderContent(final Buffer2D src, final Buffer2D dest) {
    final Box paddingBox = context.getPaddingBox();
    dest.layer(src, paddingBox.getInnerStartX(), paddingBox.getInnerStartY(), paddingBox.getInnerWidth(), paddingBox.getInnerHeight());
  }

  private void renderSection(final Box box, final Style style, final Buffer2D dest) {
    for (int row = box.getY(); row < box.getHeight() + box.getY(); row++) {
      for (int col = box.getX(); col < box.getWidth() + box.getX(); col++) {
        final Region region = box.getRegion(col, row);
        final char styleChar = style.getChar(region);
        if (styleChar != Character.MIN_VALUE) {
          dest.writeChar(col, row, styleChar);
        }
      }
    }
  }

  /*-- Getters/Setters --*/

  public Context getContext() {
    return context;
  }
}

