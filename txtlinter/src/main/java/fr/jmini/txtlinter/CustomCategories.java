package fr.jmini.txtlinter;

public enum CustomCategories {
  FORMAT("Format"),
  ;

  private final String displayName;

  CustomCategories(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }

}
