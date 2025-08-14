package com.christian.games.pojo.notation;

import com.christian.games.piece.Type;
import com.christian.games.util.Position;
import java.util.Objects;

public class Algebraic {

  private final Type type;
  private final Position starting;
  private final Position ending;
  private final boolean capture;
  private final boolean check;
  private final boolean mate;

  public Algebraic(final Type type, final Position from, final Position to) {
    this(type, from, to, false, false, false);
  }

  public Algebraic(final Type type, final Position from, final Position to,
      final boolean capture, final boolean check, final boolean mate) {
    this.type = type;
    this.starting = from;
    this.ending = to;
    this.capture = capture;
    this.check = check;
    this.mate = mate;
  }

  /*-- Methods --*/

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Algebraic that)) {
      return false;
    }
    return capture == that.capture && check == that.check && mate == that.mate && type == that.type
        && Objects.equals(starting, that.starting) && Objects.equals(ending, that.ending);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, starting, ending, capture, check, mate);
  }

  @Override
  public String toString() {
    return "AlgebraicNotation{" +
        "type=" + type +
        ", from=" + starting +
        ", to=" + ending +
        ", capture=" + capture +
        ", check=" + check +
        ", mate=" + mate +
        '}';
  }

  /*-- Getters/Setters --*/

  public Type getType() {
    return type;
  }

  public Position getStarting() {
    return starting;
  }

  public Position getEnding() {
    return ending;
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
