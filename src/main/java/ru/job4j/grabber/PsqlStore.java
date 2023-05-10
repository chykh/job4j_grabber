package ru.job4j.grabber;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nn");

    private final Connection cnn;

    public PsqlStore(Properties cfg) {
        String url = cfg.getProperty("jdbc.url");
        String login = cfg.getProperty("jdbc.username");
        String password = cfg.getProperty("jdbc.password");
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cnn.prepareStatement(
                "insert into PsqlStore(title, link, description, created) values (?,?,?,?) on conflict (link) do nothing;")) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getLink());
            statement.setString(3, post.getDescription());
            statement.setObject(4, post.getCreated());
            statement.execute();
            ResultSet genKeys = statement.getGeneratedKeys();
            if (genKeys.next()) {
                post.setId(genKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        for (Post temp : getAll()) {
            if (id == temp.getId()) {
               post = temp;
            }
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("SELECT * FROM PsqlStore ")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Post post = new Post();
                    post.setId(resultSet.getInt("id"));
                    post.setTitle(resultSet.getString("title"));
                    post.setLink(resultSet.getString("link"));
                    post.setDescription(resultSet.getString("description"));
                    post.setCreated(LocalDateTime.parse(resultSet.getString("created"), formatter));
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    static private Properties readProperty() {
        final Properties prs = new Properties();
        try (InputStream is = PsqlStore.class.getClassLoader().getResourceAsStream("psqlStore.properties")) {
            prs.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prs;
    }

    public static void main(String[] args) throws Exception {
        Store store = new PsqlStore(readProperty());

        for (int i = 0; i < 5; i++) {
            Post post = new Post();
            post.setId(i);
            post.setTitle("post" + i);
            post.setLink("link" + i);
            post.setDescription("desc" + i);
            post.setCreated(LocalDateTime.now());
            store.save(post);
        }

        System.out.println(store.getAll());
        store.close();
    }

}