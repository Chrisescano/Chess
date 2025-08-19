package com.christian.games.chess;

import static com.christian.games.piece.Type.PAWN;

import com.christian.games.parser.notation.AlgebraicNotationParser;
import com.christian.games.piece.Color;
import com.christian.games.piece.Pawn;
import com.christian.games.piece.Piece;
import com.christian.games.pojo.notation.Algebraic;
import com.christian.games.pojo.Fen;
import com.christian.games.screen.Screen;
import com.christian.games.util.BaseInitializer;
import com.christian.games.util.Position;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
  protected void doInit() {
    algebraicNotationParser = new AlgebraicNotationParser();
    board = new char[BOARD_HEIGHT][BOARD_WIDTH];

    fen.init();
    fen.getPieces().forEach(piece -> {
      if (piece.getType() == PAWN) {
        ((Pawn) piece).setMovingNorth(p1Color);
      }
      piece.init();
    });

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
    while (running) {
      Algebraic playerMove = getAndParseUserMove();
      Piece playerPiece = refineSearchResults(searchPiece(playerMove));

      if (playerPiece == null) {
        continue; //bad input or no piece exists w/ input
      }
      log.debug("Player wants to move the following piece [{}]", playerPiece);

      Position startPos = playerPiece.getPosition();
      Position endPos = playerMove.getEnding();

      playerPiece.getPosition().update(endPos);
      ChessUtility.updateMoveMap(playerPiece, endPos);
      board[startPos.getY()][startPos.getX()] = Character.MIN_VALUE;
      board[endPos.getY()][endPos.getX()] = playerPiece.getCharSymbol();

      Set<Piece> pieces = new HashSet<>();
      pieces.add(playerPiece);
      pieces.addAll(fen.getPieces().stream()
          .filter(piece -> piece.moveMapContains(List.of(startPos, endPos)))
          .toList());
      pieces.forEach(piece -> ChessUtility.markMoveMap(piece, board));

      fen.switchActiveColor();
      fen.incrementHalfMoveClock();
      if (fen.getHalfMoveClock() != 0 && fen.getHalfMoveClock() % 2 == 0) {
        fen.incrementFullMoveCounter();
      }
    }

    /*
    TODO LATEST:


    TODO:
      (2) work on next steps
        - some pieces have special moves:
          - pawn double move at start
          - pawn en passant
          - king and queen side castling
          - pieces being pinned (from king being at risk of check)
        - need to update movemap or store special moves in own list??
      (3) after move is made need to update movemap
        - utility method already exists but need to:
          - get all pieces who could interact with piece old position and update their move maps
          - get all pieces who can interact with piece new position and update their move maps
     */
  }

  /*-- Helper Methods --*/

  private Algebraic getAndParseUserMove() {
    while (true) {
      String userMove = screen.getUserResponse(String.format("Player %s Type In Your Move",
          fen.getActiveColor() == p1Color ? "1" : "2"));
      Algebraic move = algebraicNotationParser.parse(userMove);
      if (move != null) {
        return move;
      }
    }
  }

  private List<Piece> searchPiece(final Algebraic notation) {
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

    log.debug("Search criteria: [{}] resulted in {}", notation, results);
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
