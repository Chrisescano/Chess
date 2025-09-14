package com.christian.apps.chess;

import com.christian.apps.piece.Piece;
import com.christian.apps.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveManager {

  private static final Logger log = LoggerFactory.getLogger(MoveManager.class);

  private final Board board;

  public MoveManager(final Board board) {
    this.board = board;
  }

  /*-- Methods --*/

  public boolean movePiece(final Piece piece, final Position destination) {
    final Position piecePos = piece.getPosition();
    final char pieceSym = piece.getSymbol();
    final char boardSym = board.getTile(piecePos);

    if (boardSym == pieceSym) {
      board.removeSymbolAt(piecePos);
      board.placeSymbolAt(destination, pieceSym);
      piecePos.setTo(destination);
      return true;
    }

    log.warn("Could not move piece - board contained [{}] at {} instead of [{}]",
        boardSym, piecePos, pieceSym);
    return false;
  }
}
