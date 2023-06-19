package ru.job4j.kiss;
import java.util.Comparator;

public class MaxCompare<Integer> implements Comparator<Integer> {

    @Override
    public int compare(Integer left, Integer right) {
        return Math.max((int) left, (int) right);
    }

}
