package org.adventofcode.day.eight;

import org.junit.Test;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TestTreeScan {

    @Test
    public void testGetNumberOfVisibleTreesFromOutside(){
        String filename = "input.txt";
        int tree_cols = 5;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        Scanner scanner = new Scanner(inputStream);

        TreeScan treeScan = new TreeScan(scanner, tree_cols);
        int numberOfVisibleTreesFromOutside = treeScan.getVisibleTrees();

        assertEquals(numberOfVisibleTreesFromOutside, 21);
    }

    @Test
    public void testGetBestViewingScore(){
        String filename = "input2.txt";
        int tree_cols = 5;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        Scanner scanner = new Scanner(inputStream);

        TreeScan treeScan = new TreeScan(scanner, tree_cols);
        int bestViewingScore = treeScan.getViewingScore();

        assertEquals(bestViewingScore, 8);
    }
}
