package org.adventofcode.day.seven;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TestFileSystem {

    @Test
    public void testGetSumOfDirectorySizesOverSize(){
        String filename = "input.txt";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        Scanner scanner = new Scanner(inputStream);
        FileSystem fileSystem = new FileSystem();

        fileSystem.getDirectorySizes(scanner);
        int total = fileSystem.sumOfDirectorySizesOverSize(100000);
        assertEquals(95437, total);
    }
}
