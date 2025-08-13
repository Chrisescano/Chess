package com.christian.games.screen;

import com.christian.games.screen.cli.CLIScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenFactory {

  private static final Logger log = LoggerFactory.getLogger(ScreenFactory.class);

  private ScreenFactory() {}

  /*-- Methods --*/

  public static Screen create(final String type) {
    AbstractScreen screen;
    switch (type.toLowerCase()) {
      case "cli" -> screen = new CLIScreen();
      default -> {
        log.error("Unsupported screen type [{}]", type);
        return null;
      }
    }
    screen.init();
    return screen;
  }
}
