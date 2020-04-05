package com.github.vladislavgoltjajev.holidays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Holidays {

    private List<Holiday> holidays = new ArrayList<>();

    public boolean isHolidayToday() {
        return isHoliday(LocalDate.now());
    }

    public boolean isHoliday(LocalDate date) {
        return getHoliday(date) != null;
    }

    public Holiday getHolidayToday() {
        return getHoliday(LocalDate.now());
    }

    public Holiday getHoliday(LocalDate date) {
        Holiday holiday = checkFixedDateHolidays(date);

        if (holiday != null) {
            return holiday;
        }

        holiday = checkMovableDateHolidays(date);
        return holiday;
    }

    public void addFixedDateHoliday(String name, int day, int month) {
        addFixedDateHoliday(name, day, month, false);
    }

    public void addFixedDateHoliday(String name, int day, int month, boolean moveToMonday) {
        holidays.removeIf(h -> h.getDay() == day && h.getMonth() == month);
        holidays.add(new Holiday(name, day, month, moveToMonday));
    }

    public void addMovableDateHoliday(String name, MovableHolidayCode movableHolidayCode) {
        addMovableDateHoliday(name, movableHolidayCode, false);
    }

    public void addMovableDateHoliday(String name, MovableHolidayCode movableHolidayCode, boolean moveToMonday) {
        holidays.removeIf(h -> h.getMovableHolidayCode().equals(movableHolidayCode));
        holidays.add(new Holiday(name, movableHolidayCode, moveToMonday));
    }

    private Holiday checkFixedDateHolidays(LocalDate date) {
        int year = date.getYear();

        for (Holiday holiday : holidays) {
            if (holiday.getMovableHolidayCode() != null) {
                continue;
            }

            LocalDate holidayDate = LocalDate.of(year, holiday.getMonth(), holiday.getDay());

            if (holiday.isMovedToMonday()) {
                holidayDate = HolidayCalculator.getNextMonday(holidayDate);
            }

            if (date.equals(holidayDate)) {
                holiday.setDate(holidayDate);
                return holiday;
            }
        }

        return null;
    }

    private Holiday checkMovableDateHolidays(LocalDate date) {
        int year = date.getYear();

        for (Holiday holiday : holidays) {
            if (holiday.getMovableHolidayCode() == null) {
                continue;
            }

            LocalDate holidayDate = HolidayCalculator.getMovableHolidayDate(holiday.getMovableHolidayCode(), year);

            if (holiday.isMovedToMonday()) {
                holidayDate = HolidayCalculator.getNextMonday(holidayDate);
            }

            if (date.equals(holidayDate)) {
                holiday.setDate(holidayDate);
                return holiday;
            }
        }

        return null;
    }

    Holidays() {
    }
}
