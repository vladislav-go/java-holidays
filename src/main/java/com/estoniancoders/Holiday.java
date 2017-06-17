package com.estoniancoders;

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

    @Override
    public int compareTo(Holiday o) {
        return 0;
    }

    @Override
    public String toString() {
        return (day < 10 ? "0" + day: day) + "." + (month < 10 ? "0" + month : month) + " — " + name;
    }
}
