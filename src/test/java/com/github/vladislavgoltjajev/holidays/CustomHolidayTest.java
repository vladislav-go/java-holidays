package com.github.vladislavgoltjajev.holidays;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CustomHolidayTest {

    @Test
    public void testNonHoliday() {
        Holidays holidays = HolidayFactory.empty();
        LocalDate date = LocalDate.of(2020, 4, 20);
        Holiday holiday = holidays.getHoliday(date);
        assertThat(holidays.isHoliday(date)).isFalse();
        assertThat(holiday).isNull();
    }

    @Test
    public void testFixedHoliday() {
        Holidays holidays = HolidayFactory.empty();
        holidays.addFixedHoliday(new FixedHoliday("Custom holiday", 12, 1));
        LocalDate date = LocalDate.of(2020, 1, 12);
        Holiday holiday = holidays.getHoliday(date);
        assertThat(holidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
        assertThat(holiday.getName()).isEqualTo("Custom holiday");
    }

    @Test
    public void testMovableHoliday() {
        Holidays holidays = HolidayFactory.empty();
        holidays.addMovableHoliday(new MovableHoliday("Custom Easter Sunday", MovableHolidayCode.EASTER_SUNDAY));
        LocalDate date = LocalDate.of(2020, 4, 12);
        Holiday holiday = holidays.getHoliday(date);
        assertThat(holidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
        assertThat(holiday.getName()).isEqualTo("Custom Easter Sunday");
    }

    @Test
    public void testCustomHolidayJson() {
        String json = "{" +
                "  \"fixed\": [" +
                "    {" +
                "      \"day\": 1," +
                "      \"month\": 1," +
                "      \"name\": \"Custom holiday\"" +
                "    }" +
                "  ]," +
                "  \"movable\": [" +
                "    {" +
                "      \"code\": \"EASTER_SUNDAY\"," +
                "      \"name\": \"Custom Easter Sunday\"" +
                "    }" +
                "  ]" +
                "}";
        Holidays holidays = HolidayFactory.custom(json);
        LocalDate date = LocalDate.of(2020, 1, 1);
        Holiday holiday = holidays.getHoliday(date);
        assertThat(holidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
        assertThat(holiday.getName()).isEqualTo("Custom holiday");
        date = LocalDate.of(2020, 4, 12);
        holiday = holidays.getHoliday(date);
        assertThat(holidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
        assertThat(holiday.getName()).isEqualTo("Custom Easter Sunday");
    }
}