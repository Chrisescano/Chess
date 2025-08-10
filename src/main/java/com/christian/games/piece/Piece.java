package com.christian.games.piece;

import com.christian.games.chess.ChessUtility;
import com.christian.games.util.BaseInitializer;
import com.christian.games.util.Position;
import java.util.List;
import java.util.Map;

public abstract class Piece extends BaseInitializer {

  private final Position position;
  private final Type type;
  private final Color color;

  private Map<Integer, List<Position>> moveMap;
  private char charSymbol;

  public Piece(final Position position, final Type type, final Color color) {
    this.position = position;
    this.type = type;
    this.color = color;
  }

  /*-- Abstract Methods --*/

  public abstract String calculateSymbol();

  /*-- Methods --*/

  @Override
  public void doInit() {
    charSymbol = calculateSymbol().charAt(0);
    ChessUtility.generateMoveMap(this);
  }

  public int getFile() {
    return position.getX();
  }

  public int getRank() {
    return position.getY();
  }

  public boolean isEnemyOf(final Piece piece) {
    return isEnemyOf(piece.getCharSymbol());
  }

  public boolean isEnemyOf(final char symbol) {
    return Character.isUpperCase(charSymbol) ^ Character.isUpperCase(symbol);
  }

  public boolean moveMapContains(final Position position) {
    for (Integer directionId : moveMap.keySet()) {
      for (Position move : moveMap.get(directionId)) {
        if (move.equals(position)) {
          return true;
        }
      }
    }
    return false;
  }

  public String toPrettyString() {
    return String.format("%s %s @ %c%c", type, color, 'a' + position.getX(), '8' - position.getY());
  }

  @Override
  public String toString() {
    return "Piece{" +
        "position=" + position +
        ", type=" + type +
        ", color=" + color +
        '}';
  }

  /*-- Getters/Setters --*/

  public Position getPosition() {
    return position;
  }

  public Type getType() {
    return type;
  }

  public Color getColor() {
    return color;
  }

  public Map<Integer, List<Position>> getMoveMap() {
    return moveMap;
  }

  public void setMoveMap(Map<Integer, List<Position>> moveMap) {
    this.moveMap = moveMap;
  }

  public char getCharSymbol() {
    return charSymbol;
  }
}
