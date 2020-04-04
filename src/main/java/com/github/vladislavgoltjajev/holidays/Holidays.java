package com.github.vladislavgoltjajev.holidays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Holidays {

    private List<FixedHoliday> fixed = new ArrayList<>();
    private List<MovableHoliday> movable = new ArrayList<>();

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
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        Optional<FixedHoliday> fixedHolidayOptional = fixed.stream()
                .filter(h -> h.getDay() == day && h.getMonth() == month)
                .findFirst();

        if (fixedHolidayOptional.isPresent()) {
            Holiday holiday = fixedHolidayOptional.get();
            holiday.setDate(LocalDate.of(year, month, day));
            return holiday;
        }

        for (MovableHoliday movableHoliday : movable) {
            LocalDate holidayDate = MovableHolidayCalculator.getMovableHolidayDate(movableHoliday.getCode(), year);

            if (holidayDate.getDayOfMonth() == day && holidayDate.getMonthValue() == month) {
                movableHoliday.setDate(holidayDate);
                return movableHoliday;
            }
        }

        return null;
    }

    public void addFixedHoliday(FixedHoliday fixedHoliday) {
        if (fixed.stream().noneMatch(h -> h.getDay() == fixedHoliday.getDay()
                && h.getMonth() == fixedHoliday.getMonth())) {
            fixed.add(fixedHoliday);
        }
    }

    public void addMovableHoliday(MovableHoliday movableHoliday) {
        if (movable.stream().noneMatch(h -> h.getCode().equals(movableHoliday.getCode()))) {
            movable.add(movableHoliday);
        }
    }

    Holidays() {
    }
}
