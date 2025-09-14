package com.christian.apps.objects;

import com.christian.apps.piece.Piece;
import java.util.List;

public class Fen {

  private List<Piece> pieces;

  public Fen() {}

  public Fen(final List<Piece> pieces) {
    this.pieces = pieces;
  }

  /*-- Getters/Setters --*/

  public List<Piece> getPieces() {
    return pieces;
  }

  public void setPieces(List<Piece> pieces) {
    this.pieces = pieces;
  }
}
