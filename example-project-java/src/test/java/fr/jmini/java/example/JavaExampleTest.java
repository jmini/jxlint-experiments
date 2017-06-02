package fr.jmini.java.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class JavaExampleTest {

  @Test
  public void testFirst() {
    assertEquals("Hello World", JavaExample.HELLO_WORLD);
  }

  public void testSecond() {
    fail("Not yet implemented");
  }

  public void testThird() {
    fail("Not yet implemented");
  }

}
