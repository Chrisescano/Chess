package com.christian.apps.gui.cli;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ComponentTest {

  private static final Logger log = LoggerFactory.getLogger(ComponentTest.class);
  private TestComponent component;
  private Style borderStyle;
  private Style paddingStyle;

  // Simple concrete subclass of Component for testing
  private static class TestComponent extends Component {

    @Override
    protected Buffer2D renderContent() {
      char[][] data = {
          {'A', 'B'},
          {'C', 'D'}
      };
      return Buffer2D.wrap(data);
    }
  }

  @BeforeMethod
  public void setUp() {
    component = new TestComponent();

    // define border and padding styles
    borderStyle = new Style();
    borderStyle.setTop('-');
    borderStyle.setBottom('-');
    borderStyle.setLeft('|');
    borderStyle.setRight('|');
    borderStyle.setTopLeft('+');
    borderStyle.setTopRight('+');
    borderStyle.setBottomLeft('+');
    borderStyle.setBottomRight('+');

    paddingStyle = new Style();
    paddingStyle.setTop(' ');
    paddingStyle.setBottom(' ');
    paddingStyle.setLeft(' ');
    paddingStyle.setRight(' ');
    paddingStyle.setTopLeft(' ');
    paddingStyle.setTopRight(' ');
    paddingStyle.setBottomLeft(' ');
    paddingStyle.setBottomRight(' ');
  }

  @Test
  public void testRendering_default() {
    final Buffer2D rendered = component.render();
    final Buffer2D expected = Buffer2D.create(0, 0);

    Assert.assertEquals(rendered, expected);
  }

  @Test
  public void testRendering_withBorder() {
    final Context ctx = component.getContext();
    ctx.setBorderSize(6, 6);
    ctx.setBorderThickness(1, 1, 1, 1);
    ctx.setBorderStyle(borderStyle);

    final Buffer2D rendered = component.render();
    final Buffer2D expected = Buffer2D.wrap(new char[][]{
        {'+', '-', '-', '-', '-', '+'},
        {'|', 'A', 'B', '\u0000', '\u0000', '|'},
        {'|', 'C', 'D', '\u0000', '\u0000', '|'},
        {'|', '\u0000', '\u0000', '\u0000', '\u0000', '|'},
        {'|', '\u0000', '\u0000', '\u0000', '\u0000', '|'},
        {'+', '-', '-', '-', '-', '+'}
    });

    Assert.assertEquals(rendered, expected);
  }

  @Test
  public void testRendering_withPadding() {
    final Context ctx = component.getContext();
    ctx.setBorderSize(6, 6);
    ctx.setPaddingThickness(1, 1, 1, 1);
    ctx.setPaddingStyle(paddingStyle);

    final Buffer2D rendered = component.render();
    final Buffer2D expected = Buffer2D.wrap(new char[][]{
        {' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'A', 'B', '\u0000', '\u0000', ' '},
        {' ', 'C', 'D', '\u0000', '\u0000', ' '},
        {' ', '\u0000', '\u0000', '\u0000', '\u0000', ' '},
        {' ', '\u0000', '\u0000', '\u0000', '\u0000', ' '},
        {' ', ' ', ' ', ' ', ' ', ' '}
    });

    Assert.assertEquals(rendered, expected);
  }

  @Test
  public void testRendering_withBorderAndPadding() {
    final Context ctx = component.getContext();
    ctx.setBorderSize(6, 6);
    ctx.setBorderThickness(1, 1, 1, 1);
    ctx.setPaddingThickness(1, 1, 1, 1);
    ctx.setBorderStyle(borderStyle);
    ctx.setPaddingStyle(paddingStyle);

    final Buffer2D rendered = component.render();
    final Buffer2D expected = Buffer2D.wrap(new char[][]{
        {'+', '-', '-', '-', '-', '+'},
        {'|', ' ', ' ', ' ', ' ', '|'},
        {'|', ' ', 'A', 'B', ' ', '|'},
        {'|', ' ', 'C', 'D', ' ', '|'},
        {'|', ' ', ' ', ' ', ' ', '|'},
        {'+', '-', '-', '-', '-', '+'}
    });

    Assert.assertEquals(rendered, expected);
  }

  @Test
  public void testRendering_reducedSize_borderOnly() {
    final Context ctx = component.getContext();
    ctx.setBorderSize(4, 4);
    ctx.setBorderThickness(1, 1, 1, 1);
    ctx.setBorderStyle(borderStyle);

    final Buffer2D rendered = component.render();
    final Buffer2D expected = Buffer2D.wrap(new char[][]{
        {'+', '-', '-', '+'},
        {'|', 'A', 'B', '|'},
        {'|', 'C', 'D', '|'},
        {'+', '-', '-', '+'}
    });

    Assert.assertEquals(rendered, expected);
  }

  @Test
  public void testRendering_reducedSize_borderPaddingOnly() {
    final Context ctx = component.getContext();
    ctx.setBorderSize(4, 4);
    ctx.setBorderThickness(1, 1, 1, 1);
    ctx.setPaddingThickness(1, 1, 1, 1);
    ctx.setBorderStyle(borderStyle);
    ctx.setPaddingStyle(paddingStyle);

    final Buffer2D rendered = component.render();
    final Buffer2D expected = Buffer2D.wrap(new char[][]{
        {'+', '-', '-', '+'},
        {'|', ' ', ' ', '|'},
        {'|', ' ', ' ', '|'},
        {'+', '-', '-', '+'}
    });

    Assert.assertEquals(rendered, expected);
  }

  @Test
  public void testRendering_unevenSize() {
    final Context ctx = component.getContext();
    ctx.setBorderSize(6, 5);
    ctx.setBorderThickness(1, 1, 1, 1);
    ctx.setPaddingThickness(0, 1, 1, 1);
    ctx.setBorderStyle(borderStyle);
    ctx.setPaddingStyle(paddingStyle);

    final Buffer2D rendered = component.render();
    final Buffer2D expected = Buffer2D.wrap(new char[][]{
        {'+', '-', '-', '-', '-', '+'},
        {'|', ' ', 'A', 'B', ' ', '|'},
        {'|', ' ', 'C', 'D', ' ', '|'},
        {'|', ' ', ' ', ' ', ' ', '|'},
        {'+', '-', '-', '-', '-', '+'}
    });

    Assert.assertEquals(rendered, expected);
  }
}