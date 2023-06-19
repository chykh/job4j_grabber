package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    @Test
    public void maxTest1() {
        MaxMin mm = new MaxMin();
        List<Integer> integers = new ArrayList<>();
        integers.add(15);
        integers.add(10);
        integers.add(5);
        assertEquals(15, mm.max(integers, new MaxCompare()));
    }

    @Test
    public void maxTest2() {
        MaxMin mm = new MaxMin();
        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(15);
        integers.add(5);
        assertEquals(15, mm.max(integers, new MaxCompare()));
    }

    @Test
    public void maxTest3() {
        MaxMin mm = new MaxMin();
        List<Integer> integers = new ArrayList<>();
        integers.add(15);
        integers.add(5);
        integers.add(15);
        assertEquals(15, mm.max(integers, new MaxCompare()));
    }

    @Test
    public void minTest1() {
        MaxMin mm = new MaxMin();
        List<Integer> integers = new ArrayList<>();
        integers.add(5);
        integers.add(10);
        integers.add(15);
        assertEquals(5, mm.min(integers, new MinCompare()));
    }

    @Test
    public void minTest2() {
        MaxMin mm = new MaxMin();
        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(15);
        integers.add(-5);
        assertEquals(-5, mm.min(integers, new MinCompare()));
    }

    @Test
    public void minTest3() {
        MaxMin mm = new MaxMin();
        List<Integer> integers = new ArrayList<>();
        integers.add(15);
        integers.add(5);
        integers.add(15);
        assertEquals(5, mm.min(integers, new MinCompare()));
    }
}