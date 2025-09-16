package com.christian.apps.move;

import com.christian.apps.chess.Board;
import com.christian.apps.move.Move.MoveType;
import com.christian.apps.piece.Piece;
import com.christian.apps.piece.PieceType;
import com.christian.apps.util.Position;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveManager {

  private static final Logger log = LoggerFactory.getLogger(MoveManager.class);
  private static final int NOT_PAWN_MOVE = -1;
  private static final int FORWARD_PAWN_MOVE = 0;
  private static final int SIDE_PAWN_MOVE = 1;

  private final Board board;
  private final List<Move> moveLedger;

  private Position enPassantPosition;
  private int moveNumber = 1;
  private int enPassantTTL = 0;

  public MoveManager(final Board board, final Position enPassantPosition) {
    this.board = board;
    this.enPassantPosition = enPassantPosition;
    moveLedger = new ArrayList<>();
  }

  /*-- Methods --*/

  public void generatePotentialMoves(final Piece piece) {
    final List<List<Position>> potentialMoves = piece.getPotentialMoves();
    final List<Position> directions = piece.getDirections();
    final int depth = switch (piece.getType()) {
      case PAWN, KNIGHT, KING -> 1;
      case ROOK, BISHOP, QUEEN -> 7;
    };

    for (final Position direction : directions) {
      List<Position> path = new ArrayList<>();
      for (int i = 1; i <= depth; i++) {
        path.add(new Position(direction.getX() * i, direction.getY() * i));
      }
      potentialMoves.add(path);
    }
  }

  public MoveType processMove(final Piece piece, final Position destination) {
    if (piece == null || Board.isOutOfBounds(destination)) {
      log.error("Piece is null or destination: [{}] is out of bounds", destination);
      return null;
    }

    MoveType moveType = null;
    List<Position> path = piece.pathThatContains(destination);
    if (path != null) {
      final char[] boardSyms = board.getRangeOfTiles(path); //TODO: filter out of bounds pos or else AIOOB exception
      boolean isLastMoveLegal = true;

      for (int i = 0; i < boardSyms.length; i++) {
        if (!isLastMoveLegal) {
          return null;
        }

        final Position potentialMove = path.get(i);
        moveType = getMoveType(piece, potentialMove, boardSyms[i]);

        if (potentialMove.equals(destination)) {
          return moveType;
        } else if (moveType != MoveType.MOVE) {
          isLastMoveLegal = false;
        }
      }
    }

    //TODO - if path is null - check for special move
    if (piece.getType() == PieceType.PAWN) {
      moveType = checkForDoubleJump(piece, destination);

      if (moveType == null) {
        moveType = checkForEnPassant(destination);
      }

      return moveType;
    }

    //TODO - check for king castle
    if (piece.getType() == PieceType.KING) {
      //fill in logic here
    }

    return null;
  }

  public void logMove() {
    //add move records here
  }

  public boolean movePiece(final Piece piece, final Position destination) {
    final Position piecePosition = piece.getPosition();
    final char boardSym = board.getTile(piecePosition);
    final char pieceSym = piece.getSymbol();

    if (boardSym == pieceSym) {
      board.removeSymbolAt(piecePosition);
      board.placeSymbolAt(destination, pieceSym);
      translatePotentialMoves(piece.getPotentialMoves(), piecePosition, destination);
      piecePosition.setTo(destination);
      piece.incrementMoveCounter();
      moveNumber++;
      return true;
    }

    log.warn("Could not move piece - board contained [{}] at {} instead of [{}]",
        boardSym, piecePosition, pieceSym);
    return false;
  }

  public void setUpForNextMove() {
    if (enPassantTTL == 0 && enPassantPosition != null) {
      enPassantPosition = null;
    } else if (enPassantTTL == 1) {
      enPassantTTL--;
    }
  }

  /*-- Helper Methods --*/

  private void translatePotentialMoves(final List<List<Position>> potentialMoves,
      final Position oldPos, final Position newPos) {
    final Position vector = Position.difference(oldPos, newPos);
    for (final List<Position> path : potentialMoves) {
      for (final Position potentialMove : path) {
        potentialMove.translate(vector);
      }
    }
  }

  private MoveType checkForDoubleJump(final Piece piece, final Position destination) {
    if (piece.getMoveCounter() == 0 && isPawnDoubleJump(piece.getPosition(), destination)) {
      final Position singleMove = new Position(destination.getX(), destination.getY() + (piece.isWhite() ? 1 : -1));
      if (board.getTile(singleMove) == Board.EMPTY_TILE) {
        return MoveType.DOUBLE_JUMP;
      }
    }

    return null;
  }

  private MoveType checkForEnPassant(final Position destination) {
    if (enPassantPosition == null) {
      return null;
    }

    if (destination.equals(enPassantPosition)) {
      return MoveType.EN_PASSANT;
    }

    return null;
  }

  private MoveType getMoveType(final Piece piece, final Position destination, final char boardSym) {
    if (piece.getType() == PieceType.PAWN) {
      return getPawnMoveType(piece.getSymbol(), piece.getPosition(), boardSym, destination);
    }
    return getRegMoveType(piece.getSymbol(), boardSym);
  }

  private MoveType getPawnMoveType(final char pieceSym, final Position piecePos,
      final char boardSym, final Position destination) {
    final int moveStatus = isForwardPawnMove(piecePos, destination);
    if (boardSym == Board.EMPTY_TILE) {
      if (moveStatus == FORWARD_PAWN_MOVE) {
        return MoveType.MOVE;
      }
    } else {
      if (moveStatus == SIDE_PAWN_MOVE && Piece.areEnemies(pieceSym, boardSym)) {
        return MoveType.CAPTURE;
      }
    }
    return null;
  }

  private MoveType getRegMoveType(final char pieceSym, final char boardSym) {
    if (boardSym == Board.EMPTY_TILE) {
      return MoveType.MOVE;
    } else {
      if (Piece.areEnemies(pieceSym, boardSym)) {
        return MoveType.CAPTURE;
      }
    }
    return null;
  }

  public int isForwardPawnMove(final Position posA, final Position posB) {
    if (Math.abs(posA.getY() - posB.getY()) == 1) {
      if (posA.getX() == posB.getX()) {
        return FORWARD_PAWN_MOVE;
      }
      return SIDE_PAWN_MOVE;
    }
    return NOT_PAWN_MOVE;
  }

  public boolean isPawnDoubleJump(final Position posA, final Position posB) {
    if (Math.abs(posA.getY() - posB.getY()) == 2) {
      return posA.getX() == posB.getX();
    }
    return false;
  }

  /*-- Getters/Setters --*/

  public List<Move> getMoveLedger() {
    return moveLedger;
  }

  public int getMoveNumber() {
    return moveNumber;
  }
}
