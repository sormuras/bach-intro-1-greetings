class build {
  public static void main(String[] args) {
    var bach = Bach.of(args);
    var project = bach.configuration().project();
    var name = project.name().value();
    var version = project.version().value();

    bach.run("banner", "Building project %s %s".formatted(name, version));
    bach.run("build");

    if (project.spaces().main().launcher().isPresent()) {
      bach.run("banner", "Launching main module");
      bach.run("launch", 1, 2, 3);
    }
  }
}
