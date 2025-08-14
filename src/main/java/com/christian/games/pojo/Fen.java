package com.christian.games.pojo;

import static com.christian.games.piece.Color.BLACK;
import static com.christian.games.piece.Color.WHITE;

import com.christian.games.piece.Color;
import com.christian.games.piece.Piece;
import com.christian.games.util.BaseInitializer;
import com.christian.games.util.Position;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fen extends BaseInitializer {

  public static final String NEW_GAME_WHITE = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 0";
  public static final String NEW_GAME_BLACK = "RNBQKBNR/PPPPPPPP/8/8/8/8/pppppppp/rnbqkbnr b - - 0 0";

  private static final Logger log = LoggerFactory.getLogger(Fen.class);

  private final List<Piece> pieces;
  private Color activeColor;
  private boolean[] castlingRights;
  private Position enPassant;
  private Integer halfMoveClock;
  private Integer fullMoveCounter;

  public Fen(final List<Piece> pieces, final Color activeColor, final boolean[] castlingRights,
      final Position enPassant, final Integer halfMoveClock, final Integer fullMoveCounter) {
    this.pieces = pieces;
    this.activeColor = activeColor;
    this.castlingRights = castlingRights;
    this.enPassant = enPassant;
    this.halfMoveClock = halfMoveClock;
    this.fullMoveCounter = fullMoveCounter;
  }

  /*-- Methods --*/

  @Override
  public void doInit() {
    if (pieces == null) {
      log.error("Failed to initialize - Pieces is set to null");
      throw new NullPointerException("Pieces cannot be null");
    } else {
      pieces.forEach(BaseInitializer::init);
    }

    if (activeColor == null) {
      log.warn("Active Color was set to null - Defaulting to {}", WHITE);
      activeColor = WHITE;
    }

    if (castlingRights == null) {
      castlingRights = new boolean[4];
      log.warn("Castling rights was set to null - Defaulting to {}", castlingRights);
    }

    if (enPassant == null) {
      enPassant = new Position(-1, -1);
      enPassant.disable();
      log.warn("En Passant was set to null - Defaulting to {}", enPassant);
    }

    if (halfMoveClock == null) {
      halfMoveClock = 0;
      log.warn("Half Move clock was set to null - Defaulting to {}", halfMoveClock);
    }

    if (fullMoveCounter == null) {
      fullMoveCounter = 0;
      log.warn("Full Move counter was set to null - Defaulting to {}", fullMoveCounter);
    }
  }

  public void switchActiveColor() {
    activeColor = activeColor == WHITE ? BLACK : WHITE;
  }

  public void incrementHalfMoveClock() {
    halfMoveClock++;
  }

  public void incrementFullMoveCounter() {
    fullMoveCounter++;
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

  public Integer getHalfMoveClock() {
    return halfMoveClock;
  }

  public Integer getFullMoveCounter() {
    return fullMoveCounter;
  }
}
