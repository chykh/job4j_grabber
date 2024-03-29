/*
1. Если внутреннему классу не требуется доступ к членам содержащего класса, рассмотрите возможность превращения его в статический класс.
2. Убрать классы-обертки в циклах, если происходит лишняя автоупаковка-автораспаковка
3. Минимизировать использование статических переменных (храняться до конца работы программы).
4. При работе с ресурсами - заменить exception на try с ресурсами (чтобы гарантировать закрытие потока)
5. Проверить на создание лишних элементов - объектов (лишние вызовы генераторов) или методов-ссылок
 */
package ru.job4j.gc.leak;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    public static final Integer ADD_POST = 1;
    public static final Integer ADD_MANY_POST = 2;
    public static final Integer SHOW_ALL_POSTS = 3;
    public static final Integer DELETE_POST = 4;
    public static final String SELECT = "Выберите меню";
    public static final String COUNT = "Выберите количество создаваемых постов";
    public static final String TEXT_OF_POST = "Введите текст";
    public static final String EXIT = "Конец работы";
    public static final String POSTS_DELETE = "Стираем все посты";
    public static final String MENU = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        start(commentGenerator, scanner, userGenerator, postStore);
    }

    private static void start(CommentGenerator commentGenerator, Scanner scanner,
                              UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        userGenerator.generate();
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                commentGenerator.generate();
                postStore.add(new Post(text, commentGenerator.getComments()));
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                System.out.println(COUNT);
                int count = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < count; i++) {
                    createPost(commentGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(PostStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                System.out.println(POSTS_DELETE);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator, PostStore postStore, String text) {
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}