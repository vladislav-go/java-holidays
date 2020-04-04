package com.github.vladislavgoltjajev.holidays;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class FixedHoliday extends Holiday {

    private int month;
    private int day;

    public FixedHoliday(String name, int day, int month) {
        super.name = name;
        this.day = day;
        this.month = month;
    }
}
