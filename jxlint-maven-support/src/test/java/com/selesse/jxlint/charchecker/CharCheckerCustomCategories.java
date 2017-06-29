package com.selesse.jxlint.charchecker;

public enum CharCheckerCustomCategories {
  LETTER("Letter"),
  NUMBER("Number"),
  ;

  private final String displayName;

  CharCheckerCustomCategories(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }

}
