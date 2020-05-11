class Build {
  public static void main(String... args) {
    Bach.of(project -> project).build().assertSuccessful();
  }
}
