package fr.jmini.java.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaExample {
  private static final Logger LOG = LoggerFactory.getLogger(JavaExample.class);

  static final String HELLO_WORLD = "Hello World";

  public static void main(String[] args) throws Exception {
    System.out.println(HELLO_WORLD);
    LOG.warn(String.format("Value %s", HELLO_WORLD));
  }
}
