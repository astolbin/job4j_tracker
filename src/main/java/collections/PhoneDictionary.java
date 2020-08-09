package collections;

import java.util.ArrayList;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедших пользователей.
     */
    public ArrayList<Person> find(String key) {
        var result = new ArrayList<Person>();

        for (var person : this.persons) {
            if (person.getName().contains(key)
                || person.getSurname().contains(key)
                || person.getAddress().contains(key)
                || person.getPhone().contains(key)
            ) {
                result.add(person);
            }
        }

        return result;
    }
}
