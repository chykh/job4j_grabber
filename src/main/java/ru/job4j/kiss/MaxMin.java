package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin<T extends Integer> {

    private int compare(List<T> value, Comparator<Integer> comparator) {
        int result = value.get(0);
        int index = 0;
        for (int i = 1; i < value.size(); i++) {
            result = comparator.compare(result, value.get(i));
        }
        index = value.indexOf(result);
        return index;
    }

    public T max(List<T> value, Comparator comparator) {
        int index = compare(value, comparator);
        return value.get(index);
    }

    public T min(List<T> value, Comparator comparator) {
        int index = compare(value, comparator);
        return value.get(index);
    }
}