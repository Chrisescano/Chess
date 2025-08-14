package com.christian.games.parser.notation;

import static com.christian.games.piece.Type.PAWN;
import static com.christian.games.piece.Type.ROOK;

import com.christian.games.pojo.notation.Algebraic;
import com.christian.games.util.Position;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AlgebraicNotationParserTest {

  private AlgebraicNotationParser parser;
  private final Position testTo = new Position(0, 6);
  private final Position testFrom = new Position(1, 7);
  private final Position testNullPos = new Position(-1, -1);

  @BeforeClass
  public void setUp() {
    parser = new AlgebraicNotationParser();
  }

  @DataProvider(name = "parserDataProvider")
  public Object[][] parserDataProvider() {
    return new Object[][] {
        {1, "a2", new Algebraic(PAWN, testNullPos, testTo)},
        {2, "Ra2", new Algebraic(ROOK, testNullPos, testTo)},
        {3, "Rxa2", new Algebraic(ROOK, testNullPos, testTo, true, false, false)},
        {4, "Rb1a2", new Algebraic(ROOK, testFrom, testTo)},
        {5, "Ra2+", new Algebraic(ROOK, testNullPos, testTo, false, true, false)},
        {6, "Ra2#", new Algebraic(ROOK, testNullPos, testTo, false, false, true)}
    };
  }

  @Test(dataProvider = "parserDataProvider")
  public void testParser(int testCase, String inputString, Algebraic expectedResult) {
    Algebraic actualResult = parser.parse(inputString);
    Assert.assertEquals(actualResult, expectedResult, String.format("Test case %d: expected %s for input [%s]", testCase, expectedResult, inputString));
  }
}