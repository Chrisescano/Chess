package com.christian.apps.gui.cli;

import java.util.Arrays;
import java.util.Objects;

public class Buffer2D {

  private final int bufferWidth;
  private final int bufferHeight;
  private final char[][] buffer;

  private Buffer2D(final char[][] buffer, final int bufferWidth, final int bufferHeight) {
    this.buffer = buffer;
    this.bufferWidth = bufferWidth;
    this.bufferHeight = bufferHeight;
  }

  /*-- Methods --*/

  public static Buffer2D create(final int width, final int height) {
    if (width == 0 || height == 0) {
      return null;
    }
    return new Buffer2D(new char[height][width], width, height);
  }

  public static Buffer2D wrap(final char[][] data) {
    if (data == null || data.length == 0) {
      return null;
    }
    return new Buffer2D(data, data[0].length, data.length);
  }

  public void fill(final char filler) {
    for (char[] row : buffer) {
      Arrays.fill(row, filler);
    }
  }

  public char readChar(final int x, final int y) {
    if (isInsideWidthBounds(x) && isInsideHeightBounds(y)) {
      return buffer[y][x];
    }
    return Character.MIN_VALUE;
  }

  public void writeChar(final int x, final int y, final char data) {
    if (isInsideWidthBounds(x) && isInsideHeightBounds(y)) {
      buffer[y][x] = data;
    }
  }

  public void layer(final Buffer2D buffer, final int xOffset, final int yOffset, final int layerWidth, final int layerHeight) {
    if (buffer == null) {
      return;
    }

    for (int row = 0; row < bufferHeight; row++) {
      if (row == layerHeight) {
        return;
      }
      for (int col = 0; col < bufferWidth; col++) {
        if (col == layerWidth) {
          break;
        }
        final int destCol = col + xOffset;
        final int destRow = row + yOffset;
        if (isEmpty(destCol, destRow)) {
          writeChar(destCol, destRow, buffer.readChar(col, row));
        }
      }
    }
  }

  public boolean isEmpty(final int x, final int y) {
    return readChar(x, y) == Character.MIN_VALUE;
  }

  @Override
  public String toString() {
    return "Buffer2D{" +
        "bufferWidth=" + bufferWidth +
        ", bufferHeight=" + bufferHeight +
        ", buffer=" + toPrettyString() +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Buffer2D buffer2D)) {
      return false;
    }
    return bufferWidth == buffer2D.bufferWidth && bufferHeight == buffer2D.bufferHeight
        && Arrays.deepEquals(buffer, buffer2D.buffer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bufferWidth, bufferHeight, Arrays.deepHashCode(buffer));
  }

  /*-- Helper Methods --*/

  private boolean isInsideWidthBounds(final int num) {
    return num >= 0 && num < bufferWidth;
  }

  private boolean isInsideHeightBounds(final int num) {
    return num >= 0 && num < bufferHeight;
  }

  private String toPrettyString() {
    final StringBuilder builder = new StringBuilder();
    for (char[] row : buffer) {
      builder.append('[').append(row).append(']');
    }
    return builder.toString();
  }

  /*-- Getters/Setters --*/

  public int getBufferWidth() {
    return bufferWidth;
  }

  public int getBufferHeight() {
    return bufferHeight;
  }
}
