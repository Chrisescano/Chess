package com.christian.games.piece;

import com.christian.games.util.Position;
import java.util.List;
import java.util.Map;

public abstract class Piece {

  private final Position position;
  private final Type type;
  private final Color color;
  private Map<Integer, List<Position>> moveMap;

  public Piece(final Position position, final Type type, final Color color) {
    this.position = position;
    this.type = type;
    this.color = color;
  }

  /*-- Methods --*/

  public int getFile() {
    return position.getX();
  }

  public int getRank() {
    return position.getY();
  }

  public boolean isEnemyOf(final Piece piece) {
    return isEnemyOf(piece.getSymbol().charAt(0));
  }

  public boolean isEnemyOf(final char symbol) {
    return Character.isUpperCase(getSymbol().charAt(0)) ^ Character.isUpperCase(symbol);
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

  /*-- Abstract Methods --*/

  public abstract String getSymbol();

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
}
