package com.christian.apps.gui.cli;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextTest {

  private Buffer2D contentBuffer;
  private Context context;

  @BeforeClass
  public void init() {
    contentBuffer = Buffer2D.wrap(new char[][]{
        {'Q', 'T', 'W'},
        {'L', 'C', 'R'},
        {'A', 'B', 'S'}
    });
  }

  @BeforeMethod
  public void setUp() {
    context = new Context();
  }

  @Test
  public void testCalculateBounds_default() {
    context.calculateBounds(contentBuffer);

    Assert.assertEquals(context.getBorderBox(), new Box());
    Assert.assertEquals(context.getPaddingBox(), new Box());
  }

  @Test
  public void testCalculateBounds_withBorderWidthHeight() {
    context.setBorderSize(contentBuffer.getBufferWidth(), contentBuffer.getBufferHeight());
    context.calculateBounds(contentBuffer);

    final Box expectedBorder = new Box();
    expectedBorder.setWidth(3);
    expectedBorder.setHeight(3);
    final Box expectedPadding = new Box();
    expectedPadding.setWidth(3);
    expectedPadding.setHeight(3);

    Assert.assertEquals(context.getBorderBox(), expectedBorder);
    Assert.assertEquals(context.getPaddingBox(), expectedPadding);
  }

  @Test
  public void testCalculateBounds_withExtBorderWidthHeight() {
    context.setBorderSize(contentBuffer.getBufferWidth() + 1, contentBuffer.getBufferHeight() + 1);
    context.calculateBounds(contentBuffer);

    final Box expectedBorder = new Box();
    expectedBorder.setWidth(4);
    expectedBorder.setHeight(4);
    final Box expectedPadding = new Box();
    expectedPadding.setWidth(4);
    expectedPadding.setHeight(4);
    expectedPadding.setRightThickness(1);
    expectedPadding.setBottomThickness(1);

    Assert.assertEquals(context.getBorderBox(), expectedBorder);
    Assert.assertEquals(context.getPaddingBox(), expectedPadding);
  }

  @Test
  public void testGettersReturnCopies() {
    Box originalBorder = context.getBorderBox();
    originalBorder.setWidth(99);

    // Should not affect internal state
    Assert.assertNotEquals(context.getBorderBox().getWidth(), 99);
  }

  @Test
  public void testSetBorderThicknessMarksContextStale() {
    context.setBorderThickness(1, 2, 3, 4);
    // Force a calculation
    context.calculateBounds(contentBuffer);

    // After calculation, stale should be cleared
    Box expected = new Box();
    expected.setTopThickness(1);
    expected.setBottomThickness(2);
    expected.setLeftThickness(3);
    expected.setRightThickness(4);
    expected.setWidth(0);
    expected.setHeight(0);

    // Border box internal copy should still match
    Assert.assertEquals(context.getBorderBox(), expected);
  }

  @Test
  public void testPaddingAdjustmentWhenContentSmaller() {
    context.setBorderSize(10, 10); // big box
    context.setPaddingThickness(3, 3, 3, 3);
    context.calculateBounds(Buffer2D.wrap(new char[][]{
        {'A'}, {'B'}
    }));

    Box padding = context.getPaddingBox();

    // Expect top/bottom padding reset due to smaller content
    Assert.assertEquals(padding.getTopThickness(), 0);
    Assert.assertTrue(padding.getBottomThickness() >= 0);
  }

  @Test
  public void testStylesAreImportedNotReplaced() {
    Style newStyle = new Style();
    newStyle.setTop('X');
    context.setBorderStyle(newStyle);

    // Original borderStyle should now include 'X'
    Assert.assertEquals(context.getBorderStyle().getTop(), 'X');

    // Setting style again should import only set values
    Style partial = new Style();
    partial.setTop(Character.MIN_VALUE); // ignored
    partial.setLeft('L');
    context.setBorderStyle(partial);

    Style finalStyle = context.getBorderStyle();
    Assert.assertEquals(finalStyle.getTop(), 'X');  // unchanged
    Assert.assertEquals(finalStyle.getLeft(), 'L'); // imported
  }

  @Test
  public void testCalculateBoundsIdempotent() {
    context.setBorderSize(3, 3);
    context.calculateBounds(contentBuffer);

    Box first = context.getPaddingBox();
    context.calculateBounds(contentBuffer); // should do nothing since stale = false
    Box second = context.getPaddingBox();

    Assert.assertEquals(first, second);
  }
}