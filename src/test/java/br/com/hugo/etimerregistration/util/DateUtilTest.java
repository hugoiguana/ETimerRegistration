package br.com.hugo.etimerregistration.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static br.com.hugo.etimerregistration.util.DateUtil.*;
import static org.junit.Assert.*;

public class DateUtilTest {

    private static final LocalDate LOCAL_DATE_MONDAY = LocalDate.of(2019, 11, 18);
    private static final LocalDate LOCAL_DATE_TUESDAY = LocalDate.of(2019, 11, 19);
    private static final LocalDate LOCAL_DATE_WEDNESDAY = LocalDate.of(2019, 11, 20);
    private static final LocalDate LOCAL_DATE_THURSDAY = LocalDate.of(2019, 11, 21);
    private static final LocalDate LOCAL_DATE_FRIDAY = LocalDate.of(2019, 11, 22);
    private static final LocalDate LOCAL_DATE_SATURDAY = LocalDate.of(2019, 11, 23);
    private static final LocalDate LOCAL_DATE_SUNDAY = LocalDate.of(2019, 11, 24);

    private static final LocalDateTime LOCAL_DATE_TIME_MONDAY = LocalDateTime.of(LOCAL_DATE_MONDAY, LocalTime.MIN);
    private static final LocalDateTime LOCAL_DATE_TIME_TUESDAY = LocalDateTime.of(LOCAL_DATE_TUESDAY, LocalTime.MIN);
    private static final LocalDateTime LOCAL_DATE_TIME_WEDNESDAY = LocalDateTime.of(LOCAL_DATE_WEDNESDAY, LocalTime.MIN);
    private static final LocalDateTime LOCAL_DATE_TIME_THURSDAY = LocalDateTime.of(LOCAL_DATE_THURSDAY, LocalTime.MIN);
    private static final LocalDateTime LOCAL_DATE_TIME_FRIDAY = LocalDateTime.of(LOCAL_DATE_FRIDAY, LocalTime.MIN);
    private static final LocalDateTime LOCAL_DATE_TIME_SATURDAY = LocalDateTime.of(LOCAL_DATE_SATURDAY, LocalTime.MIN);
    private static final LocalDateTime LOCAL_DATE_TIME_SUNDAY = LocalDateTime.of(LOCAL_DATE_SUNDAY, LocalTime.MIN);

    private LocalDate date = LocalDate.of(2019, 1, 1);
    private String dateStr = "01/01/2019";

    @Test
    public void toLocalDate() {
        assertEquals(date, DateUtil.toLocalDate(dateStr));
    }

    @Test
    public void toLocalDate_return_null_when_passed_null() {
        assertNull(DateUtil.toLocalDate(null));
    }

    @Test
    public void toLocalDate_return_null_when_passed_empty_value() {
        DateUtil.toLocalDate("");
    }

    @Test(expected = DateTimeParseException.class)
    public void toLocalDate_throw_exception_when_passed_invalid_format_date() {
        DateUtil.toLocalDate("32/01/2019");
    }


    @Test
    public void toLocalDateOrElse_return_default_when_passed_null() {
        assertEquals(toLocalDateOrElse(null, date), date);
    }

    @Test
    public void toLocalDateOrElse_return_date_when_passed_valid_format_date() {
        assertEquals(toLocalDateOrElse(dateStr, date), date);
    }

    @Test(expected = DateTimeParseException.class)
    public void toLocalDateOrElse_throw_exception_when_passed_invalid_format_date() {
        DateUtil.toLocalDateOrElse("32/01/209", null);
    }


    @Test
    public void isWorkingDay_return_true_when_passed_a_working_day() {
        assertTrue(isWorkingDay(LOCAL_DATE_TIME_MONDAY));
        assertTrue(isWorkingDay(LOCAL_DATE_TIME_TUESDAY));
        assertTrue(isWorkingDay(LOCAL_DATE_TIME_WEDNESDAY));
        assertTrue(isWorkingDay(LOCAL_DATE_TIME_THURSDAY));
        assertTrue(isWorkingDay(LOCAL_DATE_TIME_FRIDAY));
    }

    @Test
    public void isWorkingDay_return_false_when_passed_null() {
        LocalDateTime date = null;
        assertFalse(isWorkingDay(date));
    }

    @Test
    public void isWorkingDay_return_false_when_not_passed_a_working_day() {
        assertFalse(isWorkingDay(LOCAL_DATE_TIME_SATURDAY));
        assertFalse(isWorkingDay(LOCAL_DATE_TIME_SUNDAY));
    }


    @Test
    public void isSaturday_return_true_when_passed_saturday() {
        assertTrue(isSaturday(LOCAL_DATE_TIME_SATURDAY));
    }

    @Test
    public void isSaturday_return_false_when_passed_null() {
        assertFalse(isSaturday(null));
    }

    @Test
    public void isSaturday_return_false_when_not_passed_saturday() {
        assertFalse(isSaturday(LOCAL_DATE_TIME_MONDAY));
        assertFalse(isSaturday(LOCAL_DATE_TIME_TUESDAY));
        assertFalse(isSaturday(LOCAL_DATE_TIME_WEDNESDAY));
        assertFalse(isSaturday(LOCAL_DATE_TIME_THURSDAY));
        assertFalse(isSaturday(LOCAL_DATE_TIME_FRIDAY));
        assertFalse(isSaturday(LOCAL_DATE_TIME_SUNDAY));
    }


    @Test
    public void isSunday_return_true_when_passed_sunday() {
        assertTrue(isSunday(LOCAL_DATE_TIME_SUNDAY));
    }

    @Test
    public void isSunday_return_false_when_passed_null() {
        assertFalse(isSunday(null));
    }

    @Test
    public void isSunday_return_false_when_not_passed_sunday() {
        assertFalse(isSunday(LOCAL_DATE_TIME_MONDAY));
        assertFalse(isSunday(LOCAL_DATE_TIME_TUESDAY));
        assertFalse(isSunday(LOCAL_DATE_TIME_WEDNESDAY));
        assertFalse(isSunday(LOCAL_DATE_TIME_THURSDAY));
        assertFalse(isSunday(LOCAL_DATE_TIME_FRIDAY));
        assertFalse(isSunday(LOCAL_DATE_TIME_SATURDAY));
    }

    @Test
    public void toHours_return_zero_when_passed_null() {
        assertEquals(toHour(null), BigDecimal.ZERO);
    }

    @Test
    public void toHours_return_hours() {
        assertTrue(toHour(BigDecimal.valueOf(60)).compareTo(BigDecimal.ONE) == NumberUtil.ZERO_INT);
        assertTrue(toHour(BigDecimal.valueOf(75)).compareTo(BigDecimal.valueOf(1.25)) == NumberUtil.ZERO_INT);
    }

    @Test
    public void toHourMinunteString() {
        assertEquals(DateUtil.toHourMinunteString(BigDecimal.valueOf(1.2)), "01:12");
        assertEquals(DateUtil.toHourMinunteString(BigDecimal.valueOf(01.2)), "01:12");
        assertEquals(DateUtil.toHourMinunteString(BigDecimal.valueOf(1.20)), "01:12");
        assertEquals(DateUtil.toHourMinunteString(BigDecimal.valueOf(10.50)), "10:30");
    }
}