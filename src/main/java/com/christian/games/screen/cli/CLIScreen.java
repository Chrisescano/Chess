package com.christian.games.screen.cli;

import com.christian.games.screen.Screen;
import com.christian.games.util.BaseInitializer;
import java.util.Scanner;

public class CLIScreen extends BaseInitializer implements Screen {

  private Scanner scanner;

  /*-- Methods --*/

  @Override
  public void init() {
    if (initialized) {
      return;
    }
    scanner = new Scanner(System.in);
    initialized = true;
  }

  @Override
  public String getUserMove() {
    return getLine("Type in your move");
  }

  /*-- Helper Methods --*/

  private String getLine(final String prompt) {
    System.out.printf("%s: ", prompt);
    return scanner.nextLine();
  }
}
