package build;

import de.sormuras.bach.Bach;

class Build {
  public static void main(String... args) {
    Bach.of(project -> project.name("greetings").version("1-ea")).build();
  }
}
