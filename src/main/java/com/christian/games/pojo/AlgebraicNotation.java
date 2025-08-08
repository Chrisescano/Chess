package com.christian.games.pojo;

import com.christian.games.piece.Type;
import com.christian.games.util.Position;

public class AlgebraicNotation {

  private final Type type;
  private final Position from;
  private final Position to;
  private final boolean capture;
  private final boolean check;
  private final boolean mate;

  public AlgebraicNotation(final Type type, final Position from, final Position to,
      final boolean capture, final boolean check, final boolean mate) {
    this.type = type;
    this.from = from;
    this.to = to;
    this.capture = capture;
    this.check = check;
    this.mate = mate;
  }

  /*-- Getters/Setters --*/

  public Type getType() {
    return type;
  }

  public Position getFrom() {
    return from;
  }

  public Position getTo() {
    return to;
  }

  public boolean isCapture() {
    return capture;
  }

  public boolean isCheck() {
    return check;
  }

  public boolean isMate() {
    return mate;
  }
}
