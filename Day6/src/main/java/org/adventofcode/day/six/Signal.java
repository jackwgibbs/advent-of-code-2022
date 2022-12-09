package org.adventofcode.day.six;

import java.io.InputStream;
import java.util.*;

public class Signal {
    private String signal;

    /**
     * Constructor to read in file contents and build signal object
     * @param filename - name of file containing signal
     */
    public Signal(String filename){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream(filename);
        Scanner scanner = new Scanner(input);
        this.signal = scanner.nextLine();
    }

    /**
     * Method to calculate the start of message marker,
     * @param numDistinctChars - start of message marker occurs after numDistinctChars unique characters
     * @return the start of message marker position
     */
    public int getStartOfPacketMarker(int numDistinctChars){
        ArrayList<Character> lastBits = new ArrayList<>();
        for (int i = numDistinctChars-1; i < signal.length(); i++){
            for (int j = 0; j < numDistinctChars; j++){
                if (!(lastBits.contains(signal.charAt(i - j)))){
                    lastBits.add(signal.charAt(i - j));
                }
            }

            if (lastBits.size() == numDistinctChars){
                return i + 1;
            }else{
                lastBits.clear();
            }
        }
        return -1;
    }
}
