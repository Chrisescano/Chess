package com.christian.apps.piece;

import com.christian.apps.util.Position;

public abstract class Piece {

  private final Position position;
  private final Type type;
  private final boolean isWhite;
  private char symbol;

  public Piece(final Position position, final Type type, final boolean isWhite) {
    this.position = position;
    this.type = type;
    this.isWhite = isWhite;
  }

  /*-- Methods --*/

  public static Piece toPiece(final Position position, final char symbol) {
    return switch (symbol) {
      case Pawn.WHITE_SYMBOL -> new Pawn(position, true);
      case Pawn.BLACK_SYMBOL -> new Pawn(position, false);
      case Rook.WHITE_SYMBOL -> new Rook(position, true);
      case Rook.BLACK_SYMBOL -> new Rook(position, false);
      case Knight.WHITE_SYMBOL -> new Knight(position, true);
      case Knight.BLACK_SYMBOL -> new Knight(position, false);
      case Bishop.WHITE_SYMBOL -> new Bishop(position, true);
      case Bishop.BLACK_SYMBOL -> new Bishop(position, false);
      case Queen.WHITE_SYMBOL -> new Queen(position, true);
      case Queen.BLACK_SYMBOL -> new Queen(position, false);
      case King.WHITE_SYMBOL -> new King(position, true);
      case King.BLACK_SYMBOL -> new King(position, false);
      default -> null;
    };
  }

  /*-- Getters/Setters --*/

  public Position getPosition() {
    return position;
  }

  public Type getType() {
    return type;
  }

  public boolean isWhite() {
    return isWhite;
  }

  public char getSymbol() {
    return symbol;
  }

  protected void setSymbol(char symbol) {
    this.symbol = symbol;
  }
}
