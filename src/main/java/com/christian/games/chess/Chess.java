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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chess extends BaseInitializer implements Runnable {

  public static int BOARD_WIDTH = 8;
  public static int BOARD_HEIGHT = 8;

  private static final Logger log = LoggerFactory.getLogger(Chess.class);

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
  public void doInit() {
    fen.init();

    algebraicNotationParser = new AlgebraicNotationParser();
    board = new char[BOARD_HEIGHT][BOARD_WIDTH];

    for (Piece piece : fen.getPieces()) {
      Position position = piece.getPosition();
      board[position.getY()][position.getX()] = piece.getCharSymbol();
    }

    for (Piece piece : fen.getPieces()) {
      ChessUtility.markMoveMap(piece, board);
    }

    running = true;
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
        .filter(piece -> piece.moveMapContains(notation.getEnding()));

    Position startingPos = notation.getStarting();
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

    List<String> pieceStrings = results.stream().map(Piece::toPrettyString).toList();
    StringBuilder message = new StringBuilder("Search returned: ");
    for (String pieceString : pieceStrings) {
      message.append(pieceString).append(",");
    }
    message.replace(message.length() - 1, message.length(), "");
    screen.pushNotification(message.toString());

    String userResponse = screen.getUserResponse(String.format(
        "Player %s choose by entering piece chess notation",
        fen.getActiveColor() == p1Color ? "1" : "2")
    );
    for (Piece piece : results) {
      if (piece.getPosition().toChessNotation().equalsIgnoreCase(userResponse.toLowerCase())) {
        return piece;
      }
    }
    return null;
  }
}
