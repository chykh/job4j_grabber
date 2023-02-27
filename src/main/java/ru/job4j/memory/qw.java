package ru.job4j.memory;
public class qw {
    public static void main(String[] args) {
        int x = 0;
        Object obj = new Object();
        qw example = new qw();
        example.action(obj);
    }

    public void action(Object parameter) {
        String str = parameter.toString();
        System.out.println(str);
    }
}