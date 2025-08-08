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

  private final Screen screen;
  private final Fen fen;
  private final Color p1Color;

  private AlgebraicNotationParser algebraicNotationParser;

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
    algebraicNotationParser = new AlgebraicNotationParser();
    initialized = true;
  }

  @Override
  public void run() {

  }

  /*-- Helper Methods --*/

  private AlgebraicNotation getAndParseUserMove() {
    while (true) {
      String userMove = screen.getUserMove();
      if (userMove == null) {
        continue;
      }
      AlgebraicNotation move = algebraicNotationParser.parse(userMove);
      if (move != null) {
        return move;
      }
    }
  }

  private List<Piece> searchUsing(final AlgebraicNotation notation) {
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
}
