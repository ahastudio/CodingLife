package utils;

public class GreetingPageGenerator extends PageGenerator {
    private String name;

    public GreetingPageGenerator() {
        this.name = "world";
    }

    public GreetingPageGenerator(String name) {
        if (name.isBlank()) {
            this.name = "world";
            return;
        }

        this.name = name;
    }

    @Override
    public String html() {
        return "Hello, " + name + "!";
    }
}
