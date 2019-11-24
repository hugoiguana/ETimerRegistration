package br.com.hugo.etimerregistration.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static br.com.hugo.etimerregistration.util.NumberUtil.SIXTY_BIG_D;
import static java.time.DayOfWeek.*;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Arrays.asList;

public final class DateUtil {

    public static DateTimeFormatter DT_FORMAT_DD_MM_YYYY = ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter DT_FORMAT_DD_MM_YYYY_HH_MM = ofPattern("dd/MM/yyyy HH:mm");
    public static DateTimeFormatter DT_FORMAT_HH_MM = ofPattern("HH:mm");

    public static List<DayOfWeek> WORKING_DAYS = asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);


    public static LocalDate toLocalDateOrElse(String dateStr, LocalDate dateDefault) {
        LocalDate date = toLocalDate(dateStr);
        if (date == null) {
            date = dateDefault;
        }
        return date;
    }

    public static LocalDate toLocalDate(String dateStr) {
        return dateStr == null || dateStr.isEmpty() ? null : LocalDate.parse(dateStr, DT_FORMAT_DD_MM_YYYY);
    }

    public static boolean isWorkingDay(LocalDateTime date) {
        return date != null && isWorkingDay(date.toLocalDate());
    }

    public static boolean isWorkingDay(LocalDate date) {
        return date != null && WORKING_DAYS.contains(date.getDayOfWeek());
    }

    public static boolean isSaturday(LocalDateTime date) {
        return date != null && SATURDAY.equals(date.getDayOfWeek());
    }

    public static boolean isSunday(LocalDateTime date) {
        return date != null && SUNDAY.equals(date.getDayOfWeek());
    }

    public static BigDecimal toHour(BigDecimal minutes) {
        BigDecimal hours = BigDecimal.ZERO;
        if (minutes != null) {
            hours = minutes.divide(SIXTY_BIG_D, NumberUtil.TWO_INT, RoundingMode.HALF_UP);
        }
        return hours;
    }

    public static String toHourMinunteString(BigDecimal hourMin) {
        String hourMinunteStr = "";
        if (hourMin != null) {
            BigInteger hour = hourMin.toBigInteger();
            BigInteger minute = hourMin.abs().subtract(BigDecimal.valueOf(hour.intValue()).abs()).multiply(SIXTY_BIG_D).toBigInteger();
            hourMinunteStr = String.format("%02d:%02d", hour, minute);
        }
        return hourMinunteStr;
    }
}
