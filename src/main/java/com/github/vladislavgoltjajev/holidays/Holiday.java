package com.github.vladislavgoltjajev.holidays;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
public final class Holiday {

    private String name;
    private int day;
    private int month;
    private boolean movedToMonday;
    private MovableHolidayCode movableHolidayCode;

    @Setter(AccessLevel.PACKAGE)
    private transient LocalDate date;

    Holiday(String name, int day, int month, boolean movedToMonday) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.movedToMonday = movedToMonday;
    }

    Holiday(String name, MovableHolidayCode movableHolidayCode, boolean movedToMonday) {
        this.name = name;
        this.movableHolidayCode = movableHolidayCode;
        this.movedToMonday = movedToMonday;
    }
}
