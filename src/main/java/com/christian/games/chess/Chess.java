package com.christian.games.chess;

import com.christian.games.parser.notation.AlgebraicNotationParser;
import com.christian.games.piece.Color;
import com.christian.games.piece.Piece;
import com.christian.games.pojo.AlgebraicNotation;
import com.christian.games.pojo.Fen;
import com.christian.games.screen.Screen;
import com.christian.games.util.BaseInitializer;
import com.christian.games.util.Position;
import java.util.List;
import java.util.stream.Stream;

public class Chess extends BaseInitializer implements Runnable {

  public static int BOARD_WIDTH = 8;
  public static int BOARD_HEIGHT = 8;

  private final Screen screen;
  private final Fen fen;
  private final Color p1Color;

  private AlgebraicNotationParser algebraicNotationParser;
  private char[][] board;
  private boolean running;

  public Chess(final Screen screen, final Fen fen, final Color p1Color) {
    this.screen = screen;
    this.fen = fen;
    this.p1Color = p1Color;
  }

  /*-- Methods --*/

  @Override
  public void init() {
    if (initialized) {
      return;
    }

    fen.init();

    algebraicNotationParser = new AlgebraicNotationParser();
    board = new char[BOARD_HEIGHT][BOARD_WIDTH];

    for (Piece piece : fen.getPieces()) {
      piece.init();
      Position position = piece.getPosition();
      board[position.getY()][position.getX()] = piece.getCharSymbol();
    }

    for (Piece piece : fen.getPieces()) {
      ChessUtility.markMoveMap(piece, board);
    }

    running = true;
    initialized = true;
  }

  @Override
  public void run() {
    Piece pieceToMove = null;
    boolean isUserMoveValid = false;
    while (running && !isUserMoveValid) {
      AlgebraicNotation userMove = getAndParseUserMove();
      List<Piece> results = searchPiece(userMove);
      pieceToMove = refineSearchResults(results);
      if (pieceToMove != null) {
        isUserMoveValid = true;
      }
    }

    System.out.println("Piece to move: " + pieceToMove);
  }

  /*-- Helper Methods --*/

  private AlgebraicNotation getAndParseUserMove() {
    while (true) {
      String userMove = screen.getUserResponse(String.format("Player %s Type In Your Move",
          fen.getActiveColor() == p1Color ? "1" : "2"));
      AlgebraicNotation move = algebraicNotationParser.parse(userMove);
      if (move != null) {
        return move;
      }
    }
  }

  private List<Piece> searchPiece(final AlgebraicNotation notation) {
    Stream<Piece> results = fen.getPieces().stream()
        .filter(piece -> piece.getColor() == fen.getActiveColor())
        .filter(piece -> piece.getType() == notation.getType())
        .filter(piece -> piece.moveMapContains(notation.getTo()));

    Position startingPos = notation.getTo();
    if (startingPos.getX() != -1) {
      results = results.filter(piece -> piece.getFile() == startingPos.getX());
    }

    if (startingPos.getY() != -1) {
      results = results.filter(piece -> piece.getRank() == startingPos.getY());
    }

    return results.toList();
  }

  private Piece refineSearchResults(final List<Piece> results) {
    if (results.isEmpty()) {
      return null;
    }

    if (results.size() == 1) {
      return results.getFirst();
    }

    List<String> choices = results.stream().map(Piece::toPrettyString).toList();
    StringBuilder message = new StringBuilder("Search returned many pieces: ");
    for (int i = 0; i < choices.size(); i++) {
      message.append(i + 1).append(") ").append(choices.get(i)).append(" ");
    }
    screen.pushNotification(message.toString());

    String userResponse = screen.getUserResponse(String.format(
        "Player %s choose by entering piece chess notation",
        fen.getActiveColor() == p1Color ? "1" : "2")
    );
    for (int i = 0; i < choices.size(); i++) {
      if (choices.get(i).contains(userResponse.toLowerCase())) {
        return results.get(i);
      }
    }
    return null;
  }
}
