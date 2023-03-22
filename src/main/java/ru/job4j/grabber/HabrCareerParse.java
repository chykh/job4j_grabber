package ru.job4j.grabber;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;
import java.io.IOException;
import java.time.LocalDateTime;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

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
        for (int i = 1; i <= 5; i++) {
            System.out.println("\nPAGE " + i);
            Jsoup.connect(PAGE_LINK + i).get().select(".vacancy-card__inner").forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));

                Element dateElement = row.select(".vacancy-card__date").first();
                String date = dateElement.child(0).attr("datetime");
                HabrCareerDateTimeParser hcdtp = new HabrCareerDateTimeParser();
                LocalDateTime lcd = hcdtp.parseDate(date);
                System.out.printf("%s %s %s%n", vacancyName, link, lcd);
                System.out.println(HabrCareerParse.retrieveDescription(link) + "\n");
            });
        }
    }
}