package org.adventofcode.day.six;

public class Main {
    private static final String FILENAME = "input.txt";

    public static void main(String[] args) {
        Signal signal = new Signal(FILENAME);
        // part one
        int marker = signal.getStartOfPacketMarker(4);
        System.out.println(marker);

        // part two
        marker = signal.getStartOfPacketMarker(14);
        System.out.println(marker);

    }
}
