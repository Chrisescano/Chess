package com.christian.games.pojo;

import com.christian.games.piece.Type;
import com.christian.games.util.Position;
import java.util.Objects;

public class AlgebraicNotation {

  private final Type type;
  private final Position from;
  private final Position to;
  private final boolean capture;
  private final boolean check;
  private final boolean mate;

  public AlgebraicNotation(final Type type, final Position from, final Position to) {
    this(type, from, to, false, false, false);
  }

  public AlgebraicNotation(final Type type, final Position from, final Position to,
      final boolean capture, final boolean check, final boolean mate) {
    this.type = type;
    this.from = from;
    this.to = to;
    this.capture = capture;
    this.check = check;
    this.mate = mate;
  }

  /*-- Methods --*/

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AlgebraicNotation that)) {
      return false;
    }
    return capture == that.capture && check == that.check && mate == that.mate && type == that.type
        && Objects.equals(from, that.from) && Objects.equals(to, that.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, from, to, capture, check, mate);
  }

  @Override
  public String toString() {
    return "AlgebraicNotation{" +
        "type=" + type +
        ", from=" + from +
        ", to=" + to +
        ", capture=" + capture +
        ", check=" + check +
        ", mate=" + mate +
        '}';
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
