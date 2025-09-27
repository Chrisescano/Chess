package com.christian.apps.chess;

import com.christian.apps.piece.Piece;
import com.christian.apps.piece.PieceType;
import com.christian.apps.util.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Board {

  public static final char EMPTY_TILE = '\u0000';

  private static final int BOARD_WIDTH = 8;
  private static final int BOARD_HEIGHT = 8;

  private final char[][] board;
  private final List<Piece> pieces;

  public Board() {
    board = new char[BOARD_HEIGHT][BOARD_WIDTH];
    pieces = new ArrayList<>();
  }

  /*-- Methods --*/

  public Stream<Piece> searchFor(final Boolean isWhite) {
    Stream<Piece> pieceStream = pieces.stream();

    if (isWhite != null) {
      pieceStream = pieceStream.filter(piece -> piece.isWhite() == isWhite);
    }
    return pieceStream;
  }

  public static boolean isOutOfBounds(final Position position) {
    return position.getX() < 0 || position.getX() > 7 ||
        position.getY() < 0 && position.getY() > 7;
  }

  public void registerPiece(final Piece piece) {
    pieces.add(piece);
  }

  public void unregisterPiece(final Piece piece) {
    pieces.remove(piece);
  }

  public boolean movePiece(final Piece piece, final Position destination) {
    final Position piecePos = piece.getPosition();
    final char pieceSym = piece.getSymbol();
    final char boardSym = getTile(piecePos);

    if (pieceSym == boardSym) {
      removeSymbolAt(piecePos);
      placeSymbolAt(destination, pieceSym);
      piecePos.setTo(destination);
      return true;
    }
    return false;
  }

  public char getTile(final Position position) {
    return board[position.getY()][position.getX()];
  }

  public Piece getPiece(final Position position) {
    try {
      return pieces.stream().filter(piece -> piece.getPosition().equals(position)).toList().getFirst();
    } catch (Exception ignored) {
    }
    return null;
  }

  public char[] getRangeOfTiles(final List<Position> path) {
    final char[] tileRange = new char[path.size()];
    for (int i = 0; i < tileRange.length; i++) {
      tileRange[i] = getTile(path.get(i));
    }
    return tileRange;
  }

  /*-- Helper Methods --*/

  private void placeSymbolAt(final Position position, final char symbol) {
    board[position.getY()][position.getX()] = symbol;
  }

  private void removeSymbolAt(final Position position) {
    board[position.getY()][position.getX()] = EMPTY_TILE;
  }
}
