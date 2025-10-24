package com.christian.apps.gui.cli;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StyleTest {

  private Style style;

  @BeforeClass
  public void init() {
    style = new Style();
    style.setTopLeft('Q');
    style.setTop('T');
    style.setTopRight('W');
    style.setLeft('L');
    style.setCenter('C');
    style.setRight('R');
    style.setBottomLeft('A');
    style.setBottom('B');
    style.setBottomRight('S');
  }

  @Test
  public void testGetChar() {
    Assert.assertEquals(style.getChar(Region.TOP_LEFT), 'Q');
    Assert.assertEquals(style.getChar(Region.TOP), 'T');
    Assert.assertEquals(style.getChar(Region.TOP_RIGHT), 'W');
    Assert.assertEquals(style.getChar(Region.LEFT), 'L');
    Assert.assertEquals(style.getChar(Region.CENTER), 'C');
    Assert.assertEquals(style.getChar(Region.RIGHT), 'R');
    Assert.assertEquals(style.getChar(Region.BOTTOM_LEFT), 'A');
    Assert.assertEquals(style.getChar(Region.BOTTOM), 'B');
    Assert.assertEquals(style.getChar(Region.BOTTOM_RIGHT), 'S');
    Assert.assertEquals(style.getChar(null), Character.MIN_VALUE);
  }
}