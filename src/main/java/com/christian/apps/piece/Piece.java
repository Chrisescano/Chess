package com.christian.apps.piece;

import com.christian.apps.chess.Board;
import com.christian.apps.util.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

  private final Position position;
  private final Type type;
  private final boolean isWhite;
  private char symbol;

  private final List<List<Position>> potentialMoves = new ArrayList<>();

  public Piece(final Position position, final Type type, final boolean isWhite) {
    this.position = position;
    this.type = type;
    this.isWhite = isWhite;
  }

  /*-- Abstract Methods --*/

  public abstract List<Position> getDirections();

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

  public static boolean areEnemies(final char symA, final char symB) {
    if (symA == Board.EMPTY_TILE || symB == Board.EMPTY_TILE) {
      return false;
    }
    return Character.isUpperCase(symA) ^ Character.isUpperCase(symB);
  }

  public List<Position> pathThatContains(final Position position) {
    for (final List<Position> path : potentialMoves) {
      if (path.contains(position)) {
        return path;
      }
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Piece piece)) {
      return false;
    }
    return isWhite == piece.isWhite && Objects.equals(position, piece.position)
        && type == piece.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, type, isWhite);
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

  public List<List<Position>> getPotentialMoves() {
    return potentialMoves;
  }
}
