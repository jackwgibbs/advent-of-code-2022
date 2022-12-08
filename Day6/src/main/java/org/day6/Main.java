package org.day6;

public class Main {
    private static final String FILENAME = "input.txt";

    public static void main(String[] args) {
        Signal signal = new Signal(FILENAME);
        int marker = signal.getStartOfPacketMarker(4);
        System.out.println(marker);
        marker = signal.getStartOfPacketMarker(14);
        System.out.println(marker);

    }
}