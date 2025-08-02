package com.christian.games.piece;

import com.christian.games.util.Position;

public abstract class Piece {

  private final Position position;
  private final Type type;
  private final Color color;

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
}
