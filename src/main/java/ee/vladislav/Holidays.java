package ee.vladislav;

import ee.vladislav.enums.Location;
import ee.vladislav.exceptions.HolidayException;
import ee.vladislav.objects.Holiday;
import ee.vladislav.objects.HolidayContainer;
import ee.vladislav.objects.MovableHoliday;
import ee.vladislav.utility.HolidayCalculator;
import ee.vladislav.utility.HolidayJsonParser;

import java.time.LocalDate;
import java.util.*;

/**
 * Main access point for the JavaHolidays library.
 */
public final class Holidays {

    private static Map<Location, HolidayContainer> holidayMap = new HashMap<>();
    private static HolidayCalculator holidayCalculator = new HolidayCalculator();

    static {
        HolidayJsonParser parser = new HolidayJsonParser();
        holidayMap = parser.getAllHolidays();
    }

    public static boolean isHoliday(LocalDate date, Location... locations) throws HolidayException {
        Set<Location> locationSet = new HashSet<>();
        locationSet.addAll(Arrays.asList(locations));

        for (Location location : locationSet) {
            if (isHoliday(date, location)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isHoliday(LocalDate date, Location location) throws HolidayException {
        HolidayContainer holidayContainer = holidayMap.get(location);

        for (Holiday holiday : holidayContainer.getFixedHolidays()) {
            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return true;
            }
        }

        for (MovableHoliday movableHoliday : holidayContainer.getMovableHolidays()) {
            Holiday holiday = holidayCalculator.getMovableHoliday(movableHoliday, date.getYear());

            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return true;
            }
        }

        return false;
    }

    public static List<Holiday> on(LocalDate date, Location... locations) throws HolidayException {
        List<Holiday> holidays = new ArrayList<>();
        Set<Location> locationSet = new HashSet<>();
        locationSet.addAll(Arrays.asList(locations));

        for (Location location : locationSet) {
            Holiday holiday = on(date, location);

            if (holiday != null) {
                holidays.add(holiday);
            }
        }

        Collections.sort(holidays);
        return holidays;
    }

    public static Holiday on(LocalDate date, Location location) throws HolidayException {
        HolidayContainer holidayContainer = holidayMap.get(location);

        for (Holiday holiday : holidayContainer.getFixedHolidays()) {
            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return holiday;
            }
        }

        for (MovableHoliday movableHoliday : holidayContainer.getMovableHolidays()) {
            Holiday holiday = holidayCalculator.getMovableHoliday(movableHoliday, date.getYear());

            if (date.getDayOfMonth() == holiday.getDay() && date.getMonthValue() == holiday.getMonth()) {
                return holiday;
            }
        }

        return null;
    }

    public static List<String> locations() {
        List<String> regions = new ArrayList<>();
        holidayMap.keySet().forEach(o -> regions.add(o.name()));
        Collections.sort(regions);
        return regions;
    }
}
