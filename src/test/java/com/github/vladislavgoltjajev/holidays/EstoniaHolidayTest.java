package com.github.vladislavgoltjajev.holidays;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EstoniaHolidayTest {

    Holidays estonianHolidays;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        estonianHolidays = HolidayFactory.forCountry("EST");
    }

    @Test
    public void testNonHoliday() {
        LocalDate date = LocalDate.of(2020, 4, 20);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isFalse();
        assertThat(holiday).isNull();
    }

    @Test
    public void testNewYear() {
        LocalDate date = LocalDate.of(2020, 1, 1);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testIndependenceDay() {
        LocalDate date = LocalDate.of(2020, 2, 24);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testGoodFriday() {
        LocalDate date = LocalDate.of(2020, 4, 10);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testEasterSunday() {
        LocalDate date = LocalDate.of(2020, 4, 12);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testSpringDay() {
        LocalDate date = LocalDate.of(2020, 5, 1);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testPentecost() {
        LocalDate date = LocalDate.of(2020, 5, 31);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testVictoryDay() {
        LocalDate date = LocalDate.of(2020, 6, 23);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testMidsummerDay() {
        LocalDate date = LocalDate.of(2020, 6, 24);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testIndependenceRestorationDay() {
        LocalDate date = LocalDate.of(2020, 8, 20);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testChristmasEve() {
        LocalDate date = LocalDate.of(2020, 12, 24);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testChristmasDay() {
        LocalDate date = LocalDate.of(2020, 12, 25);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }

    @Test
    public void testBoxingDay() {
        LocalDate date = LocalDate.of(2020, 12, 26);
        Holiday holiday = estonianHolidays.getHoliday(date);
        assertThat(estonianHolidays.isHoliday(date)).isTrue();
        assertThat(holiday).isNotNull();
        assertThat(holiday.getDate()).isEqualTo(date);
    }
}