package com.christian.games.pojo;

import com.christian.games.piece.Color;
import com.christian.games.piece.Piece;
import com.christian.games.util.Position;
import java.util.List;

public class Fen {

  public static final String NEW_GAME_WHITE = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 0";
  public static final String NEW_GAME_BLACK = "RNBQKBNR/PPPPPPPP/8/8/8/8/pppppppp/rnbqkbnr b - - 0 0";

  private final List<Piece> pieces;
  private final Color activeColor;
  private final boolean[] castlingRights;
  private final Position enPassant;
  private final int halfMoveClock;
  private final int fullMoveCounter;

  public Fen(final List<Piece> pieces, final Color activeColor, final boolean[] castlingRights,
      final Position enPassant, final int halfMoveClock, final int fullMoveCounter) {
    this.pieces = pieces;
    this.activeColor = activeColor;
    this.castlingRights = castlingRights;
    this.enPassant = enPassant;
    this.halfMoveClock = halfMoveClock;
    this.fullMoveCounter = fullMoveCounter;
  }

  /*-- Getters/Setters --*/

  public List<Piece> getPieces() {
    return pieces;
  }

  public Color getActiveColor() {
    return activeColor;
  }

  public boolean[] getCastlingRights() {
    return castlingRights;
  }

  public Position getEnPassant() {
    return enPassant;
  }

  public int getHalfMoveClock() {
    return halfMoveClock;
  }

  public int getFullMoveCounter() {
    return fullMoveCounter;
  }
}
