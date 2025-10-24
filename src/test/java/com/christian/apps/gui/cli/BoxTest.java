package com.christian.apps.gui.cli;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BoxTest {

  private Box box;

  @BeforeClass
  public void init() {
    box = new Box();
    box.setX(1);
    box.setY(1);
    box.setWidth(3);
    box.setHeight(3);
    box.setTopThickness(1);
    box.setBottomThickness(1);
    box.setLeftThickness(1);
    box.setRightThickness(1);
  }

  @Test
  public void testGetEndX() {
    Assert.assertEquals(box.getEndX(), 4);
  }

  @Test
  public void testGetEndY() {
    Assert.assertEquals(box.getEndY(), 4);
  }

  @Test
  public void testGetInnerStartX() {
    Assert.assertEquals(box.getInnerStartX(), 2);
  }

  @Test
  public void testGetInnerStartY() {
    Assert.assertEquals(box.getInnerStartY(), 2);
  }

  @Test
  public void testGetInnerEndX() {
    Assert.assertEquals(box.getInnerEndX(), 3);
  }

  @Test
  public void testGetInnerEndY() {
    Assert.assertEquals(box.getInnerEndY(), 3);
  }

  @Test
  public void testGetInnerWidth() {
    Assert.assertEquals(box.getInnerWidth(), 1);
  }

  @Test
  public void testGetInnerHeight() {
    Assert.assertEquals(box.getInnerHeight(), 1);
  }

  @Test
  public void testGetRegion() {
    Assert.assertEquals(box.getRegion(1, 1), Region.TOP_LEFT);
    Assert.assertEquals(box.getRegion(2, 1), Region.TOP);
    Assert.assertEquals(box.getRegion(3, 1), Region.TOP_RIGHT);
    Assert.assertEquals(box.getRegion(1, 2), Region.LEFT);
    Assert.assertEquals(box.getRegion(2, 2), Region.CENTER);
    Assert.assertEquals(box.getRegion(3, 2), Region.RIGHT);
    Assert.assertEquals(box.getRegion(1, 3), Region.BOTTOM_LEFT);
    Assert.assertEquals(box.getRegion(2, 3), Region.BOTTOM);
    Assert.assertEquals(box.getRegion(3, 3), Region.BOTTOM_RIGHT);
    Assert.assertNull(box.getRegion(1, 0));
  }
}