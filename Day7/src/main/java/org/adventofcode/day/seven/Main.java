package org.adventofcode.day.seven;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    private static final String FILENAME = "input.txt";
    public static void main(String[] args) {
        // read in file as input stream
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILENAME);
        Scanner scanner = new Scanner(inputStream);
        FileSystem fileSystem = new FileSystem();

        // part one
        fileSystem.getDirectorySizes(scanner);
        int total = fileSystem.sumOfDirectorySizesOverSize(100000);
        System.out.println(total);
    }
}
