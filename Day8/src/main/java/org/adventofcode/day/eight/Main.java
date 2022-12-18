package org.adventofcode.day.eight;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    private static final String FILENAME = "input.txt";
    private static final int TREE_COLS = 99;

    public static void main(String[] args) {
        // read in file as input stream
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILENAME);
        Scanner scanner = new Scanner(inputStream);
        TreeScan treeScan = new TreeScan(scanner, TREE_COLS);

        // part one
        int numberOfVisibleTreesFromOutside = treeScan.getVisibleTrees();

        // part two
        int bestViewingScore = treeScan.getViewingScore();

        System.out.println(numberOfVisibleTreesFromOutside);
        System.out.println(bestViewingScore);
    }
}
