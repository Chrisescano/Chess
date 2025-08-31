package com.christian.games.chess;

import com.christian.games.piece.Piece;
import com.christian.games.util.BaseInitializer;
import com.christian.games.util.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Board extends BaseInitializer {

  private static final Logger log = LoggerFactory.getLogger(Board.class);

  public static final int BOARD_WIDTH = 8;
  public static final int BOARD_HEIGHT = 8;
  public static final char EMPTY_SYMBOL = '\u0000';

  private static final int NO_PIECE = -1;

  private Map<Integer, Piece> pieceMap;
  private int[][] board;

  /*-- Methods --*/

  @Override
  protected void doInit() {
    pieceMap = new HashMap<>();
    board = new int[BOARD_HEIGHT][BOARD_WIDTH];
    for (int row = 0; row < BOARD_HEIGHT; row++) {
      for (int col = 0; col < BOARD_WIDTH; col++) {
        board[row][col] = NO_PIECE;
      }
    }
  }

  public Piece getPiece(final Position position) {
    final int pieceId = board[position.getY()][position.getX()];
    if (pieceId != NO_PIECE) {
      return pieceMap.get(pieceId);
    }
    return null;
  }

  public void placePiece(final Piece piece) {
    final int pieceId = piece.getId();
    if (!pieceMap.containsKey(pieceId)) {
      pieceMap.put(pieceId, piece);
      markBoard(piece.getPosition(), pieceId);
    }
  }

  public void removePiece(final Piece piece) {
    final int pieceId = piece.getId();
    if (pieceMap.containsKey(pieceId)) {
      pieceMap.remove(pieceId);
      markBoard(piece.getPosition(), NO_PIECE);
    }
  }

  public void updatePiece(final Piece piece, final Position newPosition) {
    final Position oldPosition = piece.getPosition();
    final int pieceId = piece.getId();
    if (pieceId == getPieceId(oldPosition)) {
      markBoard(oldPosition, NO_PIECE);
      markBoard(newPosition, pieceId);
      oldPosition.set(newPosition);
    }
  }

  public Stream<Piece> searchFor(final Predicate<Piece> query) {
    return pieceMap.values().stream().filter(query);
  }

  public void apply(final Consumer<Piece> command) {
    pieceMap.values().forEach(command);
  }

  public Character getSymbolOf(final Position position) {
    final Piece piece = getPiece(position);
    return piece == null ? EMPTY_SYMBOL : piece.getCharSymbol();
  }

  public List<Character> getSymbolsOf(final List<Position> positions) {
    return positions.stream().map(this::getSymbolOf).toList();
  }

  /*-- Helper Methods --*/

  private void markBoard(final Position position, final int id) {
    board[position.getY()][position.getX()] = id;
  }

  private int getPieceId(final Position position) {
    return board[position.getY()][position.getX()];
  }

  /*-- Getters/Setters --*/

  
}
