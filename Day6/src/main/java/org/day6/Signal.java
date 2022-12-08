package org.day6;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

public class Signal {
    private String signal;

    public Signal(String filename){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream(filename);
        Scanner scanner = new Scanner(input);
        this.signal = scanner.nextLine();
    }

    public int getStartOfPacketMarker(int numDistinctChars){
        ArrayList<Character> lastBits = new ArrayList<>();
        for (int i=numDistinctChars-1; i<signal.length();i++){
            for (int j=0; j<numDistinctChars;j++){
                if (!(lastBits.contains(signal.charAt(i-j)))){
                    lastBits.add(signal.charAt(i-j));
                }
            }

            if (lastBits.size() == numDistinctChars){
                return i+1;
            }else{
                lastBits.clear();
            }
        }
        return -1;
    }
}
