package org.echocat.kata.java.part1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
@SpringBootApplication(scanBasePackages = {"org.echocat.kata.java.part1"})
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    protected static String getHelloWorldText() {
        return "Hello world!";
    }

}
