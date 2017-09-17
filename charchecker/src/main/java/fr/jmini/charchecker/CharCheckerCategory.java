package fr.jmini.charchecker;

public enum CharCheckerCategory {
  LETTER("Letter"),
  NUMBER("Number"),
  ;

  private final String displayName;

  CharCheckerCategory(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }

}
