package com.christian.games;

import com.christian.games.chess.Chess;
import com.christian.games.parser.fen.ColorParser;
import com.christian.games.parser.fen.FenParser;
import com.christian.games.piece.Color;
import com.christian.games.pojo.Fen;
import com.christian.games.screen.Screen;
import com.christian.games.screen.ScreenFactory;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  private static final String APP_NAME = "Chess Game";
  private static final String OPTION_HELP = "h";
  private static final String LONG_OPTION_HELP = "help";
  private static final String OPTION_FEN = "f";
  private static final String LONG_OPTION_FEN = "fen";
  private static final String OPTION_P1_COLOR = "p";
  private static final String LONG_OPTION_P1_COLOR = "p1color";
  private static final String OPTION_SCREEN_TYPE = "s";
  private static final String LONG_OPTION_SCREEN_TYPE = "screen";

  public static void main(String[] args) {
    log.info("Starting {} with the following arguments: {}", APP_NAME, args);
    Options options = new Options();
    addOptions(options);

    CommandLine commandLine = null;
    try {
      CommandLineParser commandLineParser = new DefaultParser();
      commandLine = commandLineParser.parse(options, args);
    } catch (Exception e) {
      log.error("An error occurred while parsing program arguments", e);
      HelpFormatter helpFormatter = new HelpFormatter();
      helpFormatter.printHelp(APP_NAME, options);
    } finally {
      if (commandLine == null) {
        System.exit(1);
      }
    }

    Screen screen = ScreenFactory.create(commandLine.getOptionValue(OPTION_SCREEN_TYPE));

    FenParser fenParser = new FenParser();
    Fen fen = fenParser.parse(commandLine.getOptionValue(OPTION_FEN));

    ColorParser colorParser = new ColorParser(List.of("w", "white"), List.of("b", "black"), false);
    Color p1Color = colorParser.parse(commandLine.getOptionValue(OPTION_P1_COLOR));

    Chess chess = new Chess(screen, fen, p1Color);
    chess.init();
    chess.run();
  }

  public static void addOptions(final Options options) {
    options.addOption(
        Option.builder(OPTION_HELP)
            .longOpt(LONG_OPTION_HELP)
            .desc("Show program usage")
            .build()
    );
    options.addOption(
        Option.builder(OPTION_FEN)
            .longOpt(LONG_OPTION_FEN)
            .desc("Starting fen to begin the game")
            .hasArg().build()
    );
    options.addOption(
        Option.builder(OPTION_P1_COLOR)
            .longOpt(LONG_OPTION_P1_COLOR)
            .desc("Player 1 color")
            .hasArg().build()
    );
    options.addOption(
        Option.builder(OPTION_SCREEN_TYPE)
            .longOpt(LONG_OPTION_SCREEN_TYPE)
            .desc("Screen type to display game on")
            .hasArg().build()
    );
  }
}
