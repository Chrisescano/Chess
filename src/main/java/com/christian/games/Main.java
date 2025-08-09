package com.christian.games;

import com.christian.games.chess.Chess;
import com.christian.games.parser.fen.ColorParser;
import com.christian.games.parser.fen.FenParser;
import com.christian.games.piece.Color;
import com.christian.games.pojo.Fen;
import com.christian.games.screen.Screen;
import com.christian.games.screen.cli.CLIScreen;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {

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
    Options options = new Options();
    addOptions(options);

    CommandLine commandLine = null;
    try {
      CommandLineParser commandLineParser = new DefaultParser();
      commandLine = commandLineParser.parse(options, args);
    } catch (Exception e) {
      e.printStackTrace(); //should be replaced with logging
      HelpFormatter helpFormatter = new HelpFormatter();
      helpFormatter.printHelp(APP_NAME, options);
    } finally {
      if (commandLine == null) {
        System.exit(1);
      }
    }

    Screen screen = getScreen(commandLine.getOptionValue(OPTION_SCREEN_TYPE));

    FenParser fenParser = new FenParser();
    Fen fen = fenParser.parse(commandLine.getOptionValue(OPTION_FEN));

    ColorParser colorParser = new ColorParser(List.of("w", "white"), List.of("b", "black"));
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

  public static Screen getScreen(final String screenType) {
    CLIScreen cliScreen = new CLIScreen();
    cliScreen.init();
    return cliScreen;
  }
}
