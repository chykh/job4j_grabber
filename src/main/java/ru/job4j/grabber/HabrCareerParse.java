package ru.job4j.grabber;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HabrCareerParse  implements Parse {

    private static final String SOURCE_LINK = "https://career.habr.com";
    private DateTimeParser dateTimeParser;

    public HabrCareerParse() { }

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public List<Post> list(String pageLink) throws IOException {
        List<Post> postList = new ArrayList<>();
        AtomicInteger id = new AtomicInteger();
        for (int i = 1; i <= 5; i++) {
            Jsoup.connect(pageLink + i).get().select(".vacancy-card__inner").forEach(row -> {
                Post post = new Post();
                post.setId(id.getAndIncrement());

                Element titleElement = row.select(".vacancy-card__title").first();
                post.setTitle(titleElement.child(0).text());

                String link = SOURCE_LINK + titleElement.child(0).attr("href");
                post.setLink(link);

                String description = HabrCareerParse.retrieveDescription(link);
                post.setDescription(description);

                Element dateElement = row.select(".vacancy-card__date").first();
                String date = dateElement.child(0).attr("datetime");

                HabrCareerParse hcp = new HabrCareerParse(new HabrCareerDateTimeParser());
                post.setCreated(hcp.dateTimeParser.parseDate(date));

                postList.add(post);
            });
        }
        return postList;
    }

    public static String retrieveDescription(String link) {
        Element descriptionElement;
        try {
            descriptionElement = Jsoup.connect(link).get().select(".style-ugc").first();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return descriptionElement != null ? descriptionElement.text() : null;
    }

    public static void main(String[] args) throws IOException {
        final String PAGE_LINK = ("/vacancies/java_developer?page=");
        List<Post> vacancies = new HabrCareerParse().list(SOURCE_LINK + PAGE_LINK);
        vacancies.forEach(System.out::println);
    }
}