package org.day6;

import org.day6.Signal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSignal{

    @Test
    public void testGetStartOfPacketMarkerOne(){
        String filename = "input.txt";
        Signal signal = new Signal(filename);
        int marker = signal.getStartOfPacketMarker(4);
        assertEquals(5, marker);
    }

    @Test
    public void testGetStartOfPacketMarkerTwo(){
        String filename = "input2.txt";
        Signal signal = new Signal(filename);
        int marker = signal.getStartOfPacketMarker(4);
        assertEquals(6, marker);
    }

    @Test
    public void testGetStartOfPacketMarkerThree(){
        String filename = "input3.txt";
        Signal signal = new Signal(filename);
        int marker = signal.getStartOfPacketMarker(4);
        assertEquals(marker, 10);
    }

    @Test
    public void testGetStartOfPacketMarkerFour(){
        String filename = "input4.txt";
        Signal signal = new Signal(filename);
        int marker = signal.getStartOfPacketMarker(4);
        assertEquals(marker, 11);
    }
}
