import ee.vladislav.exceptions.HolidayException;
import ee.vladislav.objects.Holiday;
import ee.vladislav.Holidays;
import ee.vladislav.enums.Location;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HolidaysTest {

    @Test
    public void testHolidayChecking() throws HolidayException {
        // Fixed
        assertTrue(Holidays.isHoliday(LocalDate.of(2017, 1, 1), Location.ESTONIA));
        assertTrue(Holidays.isHoliday(LocalDate.of(2017, 2, 24), Location.ESTONIA));
        assertTrue(Holidays.isHoliday(LocalDate.of(2017, 5, 1), Location.ESTONIA));
        assertTrue(Holidays.isHoliday(LocalDate.of(2017, 6, 23), Location.ESTONIA));

        // Movable
        assertTrue(Holidays.isHoliday(LocalDate.of(2016, 3, 27), Location.ESTONIA));
        assertTrue(Holidays.isHoliday(LocalDate.of(2017, 4, 16), Location.ESTONIA));
        assertTrue(Holidays.isHoliday(LocalDate.of(2018, 4, 1), Location.ESTONIA));
    }

    @Test
    public void testHolidayGetting() throws HolidayException {
        Holiday holidayToCompare = new Holiday();
        holidayToCompare.setDay(1);
        holidayToCompare.setMonth(1);
        holidayToCompare.setName("New Year's Day");
        holidayToCompare.setDayOff(true);
        assertEquals(Holidays.on(LocalDate.of(2017, 1, 1), Location.ESTONIA), holidayToCompare);
    }
}
