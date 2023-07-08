package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

@Disabled
public class GeneratorTest {
    String template = "I am a ${name}, Who are ${subject}? ";

    @Test
    public void whenCorrectInput() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";

        Generator generator = new GeneratorClass();
        String result = generator.produce(template, args);
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenNullMap() {
        Map<String, String> args = new HashMap<>();
        args.put("name", null);
        args.put("subject", "you");

        Generator generator = new GeneratorClass();
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(Exception.class);
    }

    @Test
    public void whenNullKey() {
        Map<String, String> args = new HashMap<>();
        args.put(null, "Petr");
        args.put("subject", "you");

        Generator generator = new GeneratorClass();
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(Exception.class);
    }

    @Test
    public void whenDoubleKey() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("name", "you");

        Generator generator = new GeneratorClass();
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(Exception.class);
    }

    @Test
    public void whenWrongKey() {
        Map<String, String> args = new HashMap<>();
        args.put("n", "Petr");
        args.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";

        Generator generator = new GeneratorClass();
        String result = generator.produce(template, args);
        assertThat(expected).isNotEqualTo(result);
    }

    @Test
    public void whenWrongMap() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "P");
        args.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";

        Generator generator = new GeneratorClass();
        String result = generator.produce(template, args);
        assertThat(expected).isNotEqualTo(result);
    }
}