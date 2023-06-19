package ru.job4j.kiss;
import java.util.Comparator;

public class MinCompare<Integer> implements Comparator<Integer> {

    @Override
    public int compare(Integer left, Integer right) {
        return Math.min((int) left, (int) right);
    }

}