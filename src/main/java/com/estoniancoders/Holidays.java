package com.estoniancoders;

import com.google.gson.Gson;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Holidays {

    private static HolidayJsonParser parser = new HolidayJsonParser();

    public static boolean isHoliday(LocalDate date, Location location) {
        HolidayContainer holidayContainer = parser.getHolidaysFromJson(location);

        for (Holiday holiday : holidayContainer.getFixedHolidays()) {
            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return true;
            }
        }

        for (MovableHoliday movableHoliday : holidayContainer.getMovableHolidays()) {
            Holiday holiday = convertMovableHoliday(movableHoliday, date.getYear());

            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return true;
            }
        }

        return false;
    }

    public static Holiday on(LocalDate date, Location location) {
        HolidayContainer holidayContainer = parser.getHolidaysFromJson(location);

        for (Holiday holiday : holidayContainer.getFixedHolidays()) {
            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return holiday;
            }
        }

        for (MovableHoliday movableHoliday : holidayContainer.getMovableHolidays()) {
            Holiday holiday = convertMovableHoliday(movableHoliday, date.getYear());

            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return holiday;
            }
        }

        return null;
    }

    public static LocalDate getEasterSundayDate(int year) {
        return HolidayCalculator.getEasterSundayDate(year);
    }

    public static LocalDate getPentecostDate(int year) {
        return HolidayCalculator.getPentecostDate(year);
    }

    public static LocalDate getGoodFridayDate(int year) {
        return HolidayCalculator.getGoodFridayDate(year);
    }

    private static Holiday convertMovableHoliday(MovableHoliday movableHoliday, int year) {
        Holiday holiday = new Holiday();
        holiday.setName(movableHoliday.getName() + " (movable)");
        LocalDate date = null;

        if ("GOOD_FRIDAY".equals(movableHoliday.getCode())) {
            date = HolidayCalculator.getGoodFridayDate(year);
        }

        if ("EASTER_SUNDAY".equals(movableHoliday.getCode())) {
            date = HolidayCalculator.getEasterSundayDate(year);
        }

        if ("PENTECOST".equals(movableHoliday.getCode())) {
            date = HolidayCalculator.getPentecostDate(year);
        }

        if (date != null) {
            holiday.setDay(date.getDayOfMonth());
            holiday.setMonth(date.getMonthValue());
        }

        return holiday;
    }

    private static class HolidayJsonParser {

        private Gson gson = new Gson();

        private HolidayContainer getHolidaysFromJson(Location location) {
            InputStream in = getClass().getResourceAsStream("/holidays/" + location.name().toLowerCase() + ".json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            try {
                return gson.fromJson(IOUtils.toString(reader), HolidayContainer.class);
            } catch (IOException e) {
                return new HolidayContainer();
            }
        }
    }

    @Data
    private static class HolidayContainer {

        private List<Holiday> fixedHolidays = new ArrayList<>();
        private List<MovableHoliday> movableHolidays = new ArrayList<>();
    }

    @Data
    private static class MovableHoliday {

        private String code;
        private String name;
    }
}
