package collections;

public class TaskNumber {
    private final String desc;
    private final String number;

    public TaskNumber(String number, String desc) {
        this.desc = desc;
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public String getNumber() {
        return number;
    }
}
