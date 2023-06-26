package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public String readFile(Path path) {
        try (BufferedReader read = new BufferedReader(new FileReader(path.toFile()))) {
            String string = read.lines().collect(Collectors.joining(" "));
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            }
        return null;
    }

    @Override
    protected String load(String key) {
        System.out.println(cachingDir + key);
        Path absolutePath = Path.of(cachingDir + key).toAbsolutePath();
        System.out.println(absolutePath);

        if (get(key) == null) {
            if (Files.notExists(absolutePath)) {
                System.out.println("file not found");
                return null;
            } else {
                String string = readFile(absolutePath);
                SoftReference<String> soft = new SoftReference<>(string);
                cache.put(key, soft);
                System.out.println("PUT!");
                return string;
            }
        } else {
            System.out.println("GET!");
        return cache.get(key).get();
        }
    }

    public static void main(String[] args) {
        AbstractCache<String, String> dfc = new DirFileCache("src/main/java/ru/job4j/cache/files/");

        System.out.println(dfc.load("surnames.txt"));
        System.out.println(dfc.load("surnames.txt"));
        System.out.println(dfc.load("surnames.txt"));
    }

}
