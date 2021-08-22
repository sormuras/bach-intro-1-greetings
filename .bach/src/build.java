import com.github.sormuras.bach.Bach;
import java.lang.module.ModuleFinder;

class build {
  public static void main(String[] args) {
    try (var bach = new Bach(args)) {
      var options = bach.configuration().projectOptions();
      var name = options.name().orElse("unnamed");
      var version = options.version().map(Object::toString).orElse("0-ea");

      bach.logCaption("Build project %s".formatted(name));
      bach.run(
          "javac",
          javac ->
              javac
                  .with("--module", "com.greetings")
                  .with("--module-version", version)
                  .with("--module-source-path", ".")
                  .with("-d", bach.path().workspace("classes")));
      bach.run("jar",
          jar -> jar
              .with("--create")
              .with("--file", bach.path().workspace("com.greetings.jar"))
              .with("--main-class", "com.greetings.Main")
              .with("-C", bach.path().workspace("classes", "com.greetings"),"."));

      bach.logCaption("Launch module com.greetings");
      bach.run(ModuleFinder.of(bach.path().workspace()), "com.greetings", greet -> greet.with("?"))
          .output()
          .lines()
          .forEach(System.out::println);
    }
  }
}
