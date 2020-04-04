package com.github.vladislavgoltjajev.holidays;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MovableHoliday extends Holiday {

    private MovableHolidayCode code;

    public MovableHoliday(String name, MovableHolidayCode code) {
        super.name = name;
        this.code = code;
    }
}
