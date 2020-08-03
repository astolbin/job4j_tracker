package stream;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SchoolTest {
    private List<Student> students;

    @Test
    public void whenFilterStudentsForClassA() {
        Predicate<Student> scoreFilter = student -> student.getScore() >= 70;
        List<Student> studentsA = new School().collect(students, scoreFilter);

        assertThat(checkStudentsScore(studentsA, 70, 100), is(true));
    }

    @Test
    public void whenFilterStudentsForClassB() {
        Predicate<Student> scoreFilter =
                student -> student.getScore() >= 50
                        && student.getScore() < 70;
        List<Student> studentsB = new School().collect(students, scoreFilter);

        assertThat(checkStudentsScore(studentsB, 50, 69), is(true));
    }

    @Test
    public void whenFilterStudentsForClassC() {
        Predicate<Student> scoreFilter = student -> student.getScore() < 50;
        List<Student> studentsC = new School().collect(students, scoreFilter);

        assertThat(checkStudentsScore(studentsC, 1, 49), is(true));
    }

    @Before
    public void initStudents() {
        students = List.of(
                new Student("11111", 65),
                new Student("22222", 35),
                new Student("33333", 85),
                new Student("44444", 95),
                new Student("55555", 13),
                new Student("66666", 70)
        );
    }

    private boolean checkStudentsScore(List<Student> students, int scoreFrom, int scoreTo) {
        int wrongScore = (int) students.stream().filter(
                student -> student.getScore() < scoreFrom
                        || student.getScore() > scoreTo
        ).count();

        return wrongScore == 0;
    }
}