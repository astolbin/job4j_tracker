package stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListConverter {
    public Map<String, Student> toMap(List<Student> students) {
        return students.stream()
                .collect(Collectors.toMap(
                        Student::getSurname,
                        student -> student,
                        (existing, replacement) -> replacement
                ));
    }
}
