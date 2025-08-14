package com.christian.games.piece;

import com.christian.games.chess.ChessUtility;
import com.christian.games.util.BaseInitializer;
import com.christian.games.util.Position;
import java.util.List;

public abstract class Piece extends BaseInitializer {

  private final Position position;
  private final Type type;
  private final Color color;

  private List<Position> moves;
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

  public boolean isOneTileAwayFrom(final Position position) {
    int xDiff = position.getX() - this.position.getX();
    int yDiff = position.getY() - this.position.getY();
    return xDiff >= -1 && xDiff <= 1 && yDiff >= -1 && yDiff <= 1;
  }

  public boolean moveMapContains(final Position position) {
    return moves.stream().anyMatch(move -> move.equals(position) && move.isEnabled());
  }

  public String toPrettyString() {
    return String.format("%s %s @ %s", color, type, position.toChessNotation());
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

  public List<Position> getMoves() {
    return moves;
  }

  public void setMoves(List<Position> moves) {
    this.moves = moves;
  }

  public char getCharSymbol() {
    return charSymbol;
  }
}
