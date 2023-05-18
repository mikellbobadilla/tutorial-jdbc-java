package com.mikellbobadilla;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

  private static final Logger LOG = Logger.getLogger(Main.class.getName());

  public static void main(String... args) {
    try {
      new EscuelaDB();
    }catch (SQLException exc){
      LOG.log(Level.WARNING, exc.getMessage(), exc);
    }
  }
}