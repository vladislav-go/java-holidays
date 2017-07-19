package ee.vladislav.objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public final class Holiday implements Comparable<Holiday> {

    private int day;
    private int month;
    private String name;
    private boolean dayOff;

    @Override
    public int compareTo(Holiday o) {
        if (day == o.day
                && month == o.month
                && name.compareTo(o.name) == 0) {
            return 0;
        }

        if (month > o.month && day > o.day && name.compareTo(o.name) > 0) {
            return 1;
        }

        return -1;
    }

    @Override
    public String toString() {
        return (day < 10 ? "0" + day : day) + "."
                + (month < 10 ? "0" + month : month) + " — "
                + name + (dayOff ? " (non-working day)" : "");
    }
}
