package build;

import de.sormuras.bach.Bach;

class Build {
  public static void main(String... args) {
    Bach.of(project -> project.withVersion("1-ea")).buildProject();
  }
}
