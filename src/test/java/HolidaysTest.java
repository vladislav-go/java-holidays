import com.estoniancoders.Holidays;
import com.estoniancoders.Location;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HolidaysTest {

    @Test
    public void testFixedHolidays() {
        assertTrue("should count 01.01.2017 as a public holiday in Estonia",
                Holidays.isHoliday(LocalDate.of(2017, 1, 1), Location.ESTONIA));
        assertTrue("should count 24.02.2017 as a public holiday in Estonia",
                Holidays.isHoliday(LocalDate.of(2017, 2, 24), Location.ESTONIA));
        assertTrue("should count 01.05.2017 as a public holiday in Estonia",
                Holidays.isHoliday(LocalDate.of(2017, 5, 1), Location.ESTONIA));
        assertTrue("should count 24.06.2017 as a public holiday in Estonia",
                Holidays.isHoliday(LocalDate.of(2017, 6, 23), Location.ESTONIA));
    }

    @Test
    public void testMovableHolidays() {
        assertTrue("should count 27.03.2016 as a public holiday in Estonia",
                Holidays.isHoliday(LocalDate.of(2016, 3, 27), Location.ESTONIA));
        assertTrue("should count 16.04.2017 as a public holiday in Estonia",
                Holidays.isHoliday(LocalDate.of(2017, 4, 16), Location.ESTONIA));
        assertTrue("should count 01.04.2018 as a public holiday in Estonia",
                Holidays.isHoliday(LocalDate.of(2018, 4, 1), Location.ESTONIA));
    }
}
