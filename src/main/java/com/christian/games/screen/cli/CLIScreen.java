package com.christian.games.screen.cli;

import com.christian.games.screen.AbstractScreen;
import java.util.Scanner;

public class CLIScreen extends AbstractScreen {

  private Scanner scanner;

  /*-- Methods --*/

  @Override
  public void doInit() {
    scanner = new Scanner(System.in);
  }

  @Override
  public String getUserResponse(final String prompt) {
    return getLine(prompt);
  }

  @Override
  public void pushNotification(String message) {
    System.out.println(message);
  }

  /*-- Helper Methods --*/

  private String getLine(final String prompt) {
    System.out.printf("%s: ", prompt);
    return scanner.nextLine();
  }
}
