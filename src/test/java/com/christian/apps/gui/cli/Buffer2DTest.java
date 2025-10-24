package com.christian.apps.gui.cli;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Buffer2DTest {

  private Buffer2D buffer2D;
  private Buffer2D testContent;

  @BeforeClass
  public void init() {
    testContent = Buffer2D.wrap(new char[][]{
        {'Q', 'T', 'W'},
        {'L', 'C', 'R'},
        {'A', 'B', 'S'}
    });
  }

  @BeforeMethod
  public void setUp() {
    buffer2D = Buffer2D.create(3, 3);
  }

  @Test
  public void testLayer_noOffset_fullWidthHeight() {
    buffer2D.layer(testContent, 0, 0, testContent.getBufferWidth(), testContent.getBufferHeight());
    Assert.assertEquals(buffer2D, testContent);
  }

  @Test
  public void testLayer_withOffsets_noWidthHeight() {
    buffer2D.layer(testContent, 0, 0, 0, 0);
    Assert.assertEquals(buffer2D, Buffer2D.create(3, 3));
  }

  @Test
  public void testLayer_withOffsets_fullWidthHeight() {
    buffer2D.layer(testContent, 1, 1, testContent.getBufferWidth(), testContent.getBufferHeight());

    Assert.assertEquals(buffer2D, Buffer2D.wrap(new char[][]{
        {'\u0000', '\u0000', '\u0000'},
        {'\u0000', 'Q', 'T'},
        {'\u0000', 'L', 'C'}
    }));
  }

  @Test
  public void testLayer_noOffsets_partialWidthHeight() {
    buffer2D.layer(testContent, 0, 0, testContent.getBufferWidth() - 1,
        testContent.getBufferHeight() - 1);

    Assert.assertEquals(buffer2D, Buffer2D.wrap(new char[][]{
        {'Q', 'T', '\u0000'},
        {'L', 'C', '\u0000'},
        {'\u0000', '\u0000', '\u0000'}
    }));
  }

  @Test
  public void testLayer_withOffsets_partialWidthHeight() {
    buffer2D.layer(testContent, 1, 1, testContent.getBufferWidth() - 1,
        testContent.getBufferHeight() - 1);

    Assert.assertEquals(buffer2D, Buffer2D.wrap(new char[][]{
        {'\u0000', '\u0000', '\u0000'},
        {'\u0000', 'Q', 'T'},
        {'\u0000', 'L', 'C'}
    }));
  }

  @Test
  public void testLayer_withNegOffsets_fullWidthHeight() {
    buffer2D.layer(testContent, -1, -1, testContent.getBufferWidth(),
        testContent.getBufferHeight());

    Assert.assertEquals(buffer2D, Buffer2D.wrap(new char[][]{
        {'C', 'R', '\u0000'},
        {'B', 'S', '\u0000'},
        {'\u0000', '\u0000', '\u0000'}
    }));
  }
}