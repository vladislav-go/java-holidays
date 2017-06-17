import com.estoniancoders.Holiday;
import com.estoniancoders.Holidays;
import com.estoniancoders.Location;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HolidaysTest {

    @Test
    public void testHolidayChecking() {
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
    public void testHolidayGetting() {
        Holiday holidayToCompare = new Holiday();
        holidayToCompare.setDay(1);
        holidayToCompare.setMonth(1);
        holidayToCompare.setName("New Year's Day");
        assertEquals(Holidays.on(LocalDate.of(2017, 1, 1), Location.ESTONIA), holidayToCompare);
    }

    @Test
    public void testEasterSundayDate() {
        assertEquals(Holidays.getEasterSundayDate(2016), LocalDate.of(2016, 3, 27));
        assertEquals(Holidays.getEasterSundayDate(2017), LocalDate.of(2017, 4, 16));
        assertEquals(Holidays.getEasterSundayDate(2018), LocalDate.of(2018, 4, 1));
    }

    @Test
    public void testGoodFridayDate() {
        assertEquals(Holidays.getGoodFridayDate(2016), LocalDate.of(2016, 3, 25));
        assertEquals(Holidays.getGoodFridayDate(2017), LocalDate.of(2017, 4, 14));
        assertEquals(Holidays.getGoodFridayDate(2018), LocalDate.of(2018, 3, 30));
    }

    @Test
    public void testPentecostDate() {
        assertEquals(Holidays.getPentecostDate(2016), LocalDate.of(2016, 5, 15));
        assertEquals(Holidays.getPentecostDate(2017), LocalDate.of(2017, 6, 4));
        assertEquals(Holidays.getPentecostDate(2018), LocalDate.of(2018, 5, 20));
    }
}
