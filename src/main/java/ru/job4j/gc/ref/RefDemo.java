package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class RefDemo {
    private String value;

    RefDemo() {
        value = String.valueOf(System.currentTimeMillis());
    }

    public String getValue() {
        return value;
    }

    public void finalize() {
        System.out.println("Объект стерт");
    }

    public static void main(String[] args) throws Exception {
        RefDemo rd = new RefDemo();
        WeakReference<RefDemo> weak = new WeakReference<>(rd);
        SoftReference<RefDemo> soft = new SoftReference<>(rd);
        System.out.println(rd.getValue());
        System.out.println(weak.get().getValue());
        System.out.println(soft.get().getValue());

        rd = null;
        soft.clear();
        weak.clear();
        System.gc();
        TimeUnit.SECONDS.sleep(5);

        System.out.println(rd);
        System.out.println(weak.get());
        System.out.println(soft.get());
    }
}
