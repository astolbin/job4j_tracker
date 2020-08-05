package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ListConverterTest {
    @Test
    public void whenListConvertToMap() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 50),
                new Student("Ivanov", 50),
                new Student("Petrov", 60),
                new Student("Petrov", 70)
        );

        Map<String, Student> studentMap = new ListConverter().toMap(students);

        assertThat(studentMap.get("Ivanov"), is(new Student("Ivanov", 50)));
        assertThat(studentMap.get("Petrov"), is(new Student("Petrov", 70)));
    }
}