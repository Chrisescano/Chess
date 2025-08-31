package com.christian.games.piece;

import com.christian.games.util.BaseInitializer;
import com.christian.games.util.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece extends BaseInitializer {

  private final Position position;
  private final Type type;
  private final Color color;

  private List<Position> specialMoves;
  private Map<Integer, List<Position>> moveMap;
  private int id;
  private char charSymbol;
  private boolean moved;

  public Piece(final Position position, final Type type, final Color color) {
    this.position = position;
    this.type = type;
    this.color = color;
  }

  /*-- Abstract Methods --*/

  public abstract String calculateSymbol();

  public abstract List<Position> getDirections();

  /*-- Methods --*/

  @Override
  protected void doInit() {
    specialMoves = new ArrayList<>();
    charSymbol = calculateSymbol().charAt(0);
    id = position.toId();
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

  public boolean isOneTileAwayFrom(final Position position) {
    int xDiff = position.getX() - this.position.getX();
    int yDiff = position.getY() - this.position.getY();
    return xDiff >= -1 && xDiff <= 1 && yDiff >= -1 && yDiff <= 1;
  }

  public String toPrettyString() {
    return String.format("%s %s @ %s", color, type, position.toChessNotation());
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Piece piece)) {
      return false;
    }
    return Objects.equals(position, piece.position) && type == piece.type
        && color == piece.color;
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, type, color);
  }

  @Override
  public String toString() {
    return "Piece{" +
        "position=" + position +
        ", type=" + type +
        ", color=" + color +
        ", moveMap=" + moveMap +
        ", moved=" + moved +
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

  public List<Position> getSpecialMoves() {
    return specialMoves;
  }

  public void setSpecialMoves(List<Position> specialMoves) {
    this.specialMoves = specialMoves;
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

  public boolean isMoved() {
    return moved;
  }

  public void setMoved(boolean moved) {
    this.moved = moved;
  }

  public int getId() {
    return id;
  }
}
