package com.christian.games.parser.fen;

import static com.christian.games.piece.Color.BLACK;
import static com.christian.games.piece.Color.WHITE;

import com.christian.games.chess.Board;
import com.christian.games.parser.Parser;
import com.christian.games.piece.Bishop;
import com.christian.games.piece.King;
import com.christian.games.piece.Knight;
import com.christian.games.piece.Piece;
import com.christian.games.piece.Pawn;
import com.christian.games.piece.Queen;
import com.christian.games.piece.Rook;
import com.christian.games.util.Position;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardStateParser implements Parser<String, Board> {

  private static final Logger log = LoggerFactory.getLogger(BoardStateParser.class);

  private static final char SLASH = '/';

  @Override
  public Board parse(String input) {
    if (input == null) {
      return null;
    }

    log.info("Parsing input [{}]", input);
    Set<Piece> pieces = new HashSet<>();
    int file = 0, rank = 0, total = 0;
    for (char token : input.toCharArray()) {
      Piece piece = toPiece(token, file, rank);
      if (piece != null) {
        pieces.add(piece);
        file++;
        total++;
      } else if (token >= '1' && token <= '8') {
        int num = token - '0';
        file += num;
        total += num;
      } else if (token == SLASH) {
        file = 0;
        rank++;
      } else {
        log.error("Unrecognized token [{}]", token);
        break;
      }
    }

    if (total == 64) {
      Board board = new Board();
      board.init();
      pieces.forEach(board::placePiece);
      return board;
    }

    log.error("Input accounted for [{}] tiles instead of 64", total);
    return null;
  }

  @Override
  public String unparse(Board input) {
    return "";
  }

  /*-- Helper Methods --*/

  private Piece toPiece(final char token, final int file, final int rank) {
    return switch (String.valueOf(token)) {
      case Pawn.WHITE_SYMBOL -> new Pawn(new Position(file, rank), WHITE);
      case Pawn.BLACK_SYMBOL -> new Pawn(new Position(file, rank), BLACK);
      case Rook.WHITE_SYMBOL -> new Rook(new Position(file, rank), WHITE);
      case Rook.BLACK_SYMBOL -> new Rook(new Position(file, rank), BLACK);
      case Knight.WHITE_SYMBOL -> new Knight(new Position(file, rank), WHITE);
      case Knight.BLACK_SYMBOL -> new Knight(new Position(file, rank), BLACK);
      case Bishop.WHITE_SYMBOL -> new Bishop(new Position(file, rank), WHITE);
      case Bishop.BLACK_SYMBOL -> new Bishop(new Position(file, rank), BLACK);
      case Queen.WHITE_SYMBOL -> new Queen(new Position(file, rank), WHITE);
      case Queen.BLACK_SYMBOL -> new Queen(new Position(file, rank), BLACK);
      case King.WHITE_SYMBOL -> new King(new Position(file, rank), WHITE);
      case King.BLACK_SYMBOL -> new King(new Position(file, rank), BLACK);
      default -> null;
    };
  }
}
