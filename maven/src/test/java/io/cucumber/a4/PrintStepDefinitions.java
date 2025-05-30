package io.cucumber.a4;

import io.cucumber.core.cli.Main;

public class PrintStepDefinitions {
    public static void main(String[] args) {
        // This will print all available step definitions
        Main.run(new String[]{"--dry-run", "--glue", "io.cucumber.a4.stepdefinitions"}, Thread.currentThread().getContextClassLoader());
    }
}
