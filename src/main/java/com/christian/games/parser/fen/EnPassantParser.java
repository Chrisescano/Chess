package com.christian.games.parser.fen;

import com.christian.games.parser.Parser;
import com.christian.games.util.Position;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnPassantParser implements Parser<String, Position> {

  private static final Logger log = LoggerFactory.getLogger(EnPassantParser.class);

  private final Pattern pattern = Pattern.compile("([abcdefgh])([36])");

  @Override
  public Position parse(String input) {
    if (input == null) {
      return null;
    }

    log.info("Parsing input {}", input);
    Matcher matcher = pattern.matcher(input);
    if (!matcher.matches()) {
      return null;
    }

    int file = 'a' - matcher.group(1).charAt(0);
    int rank = '1' - matcher.group(2).charAt(0);
    return new Position(file, rank);
  }

  @Override
  public String unparse(Position input) {
    return "";
  }
}
