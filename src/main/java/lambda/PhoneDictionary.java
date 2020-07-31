package lambda;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private final ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> equalsAddress = (person) -> person.getAddress().equals(key);
        Predicate<Person> equalsName = (person) -> person.getName().equals(key);
        Predicate<Person> equalsSurname = (person) -> person.getSurname().equals(key);
        Predicate<Person> equalsPhone = (person) -> person.getPhone().equals(key);
        Predicate<Person> combine = equalsAddress
                .or(equalsName)
                .or(equalsSurname)
                .or(equalsPhone);

        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
