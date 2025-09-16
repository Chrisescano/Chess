package com.christian.apps.move;

import com.christian.apps.chess.Board;
import com.christian.apps.util.Position;

public class Move {

  private final Position oldPosition;
  private final Position newPosition;
  private MoveType moveType;
  private final char pieceSymbol;
  private char newPosSymbol;
  private boolean eligible;
  private boolean eligibleNotSet = true;

  public Move(final char pieceSymbol, final Position oldPosition, final Position newPosition) {
    this.pieceSymbol = pieceSymbol;
    this.oldPosition = oldPosition;
    this.newPosition = newPosition;
  }

  /*-- Inner Class --*/

  public enum MoveType {
    MOVE, CAPTURE, DOUBLE_JUMP
  }

  /*-- Methods --*/

  /*-- Getters/Setters --*/

  public Position getOldPosition() {
    return oldPosition;
  }
  public Position getNewPosition() {
    return newPosition;
  }

  public MoveType getMoveType() {
    return moveType;
  }

  public void setMoveType(final MoveType moveType) {
    if (this.moveType == null) {
      this.moveType = moveType;
    }
  }

  public char getPieceSymbol() {
    return pieceSymbol;
  }

  public char getNewPosSymbol() {
    return newPosSymbol;
  }

  public void setNewPosSymbol(final char newPosSymbol) {
    if (this.newPosSymbol == Board.EMPTY_TILE) {
      this.newPosSymbol = newPosSymbol;
    }
  }

  public boolean isEligible() {
    return eligible;
  }

  public void setEligible(final boolean eligible) {
    if (eligibleNotSet) {
      this.eligible = eligible;
      eligibleNotSet = false;
    }
  }
}
