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

/**
 * Utility class for date.
 *
 * @author Hugo Mota
 * @since  1.0
 */
public final class DateUtil {

    public static DateTimeFormatter DT_FORMAT_DD_MM_YYYY = ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter DT_FORMAT_DD_MM_YYYY_HH_MM = ofPattern("dd/MM/yyyy HH:mm");
    public static DateTimeFormatter DT_FORMAT_HH_MM = ofPattern("HH:mm");

    /** A List of business days */
    public static List<DayOfWeek> BUSINESS_DAYS = asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

    /**
     * Transform a string to LocalDate or return a default date if null
     *
     * @param dateStr
     * @param dateDefault
     * @return {@code LocalDate}
     */
    public static LocalDate toLocalDateOrElse(String dateStr, LocalDate dateDefault) {
        LocalDate date = toLocalDate(dateStr);
        if (date == null) {
            date = dateDefault;
        }
        return date;
    }

    /**
     * Transform a string to LocalDate.
     *
     * @param dateStr
     * @return {@code LocalDate}
     */
    public static LocalDate toLocalDate(String dateStr) {
        return dateStr == null || dateStr.isEmpty() ? null : LocalDate.parse(dateStr, DT_FORMAT_DD_MM_YYYY);
    }

    /**
     * returns if it is business day
     *
     * @param date
     * @return {@code boolean}
     */
    public static boolean isBusinessDay(LocalDateTime date) {
        return date != null && isBusinessDay(date.toLocalDate());
    }

    /**
     * returns if it is business day
     *
     * @param date
     * @return {@code boolean}
     */
    public static boolean isBusinessDay(LocalDate date) {
        return date != null && BUSINESS_DAYS.contains(date.getDayOfWeek());
    }

    /**
     * returns if it is saturday day
     *
     * @param date
     * @return {@code boolean}
     */
    public static boolean isSaturday(LocalDateTime date) {
        return date != null && SATURDAY.equals(date.getDayOfWeek());
    }

    /**
     * returns if it is sunday day
     *
     * @param date
     * @return {@code boolean}
     */
    public static boolean isSunday(LocalDateTime date) {
        return date != null && SUNDAY.equals(date.getDayOfWeek());
    }

    /**
     * returns minutes into hours
     *
     * @param minutes
     * @return {@code BigDecimal}
     */
    public static BigDecimal toHour(BigDecimal minutes) {
        BigDecimal hours = BigDecimal.ZERO;
        if (minutes != null) {
            hours = minutes.divide(SIXTY_BIG_D, NumberUtil.TWO_INT, RoundingMode.HALF_UP);
        }
        return hours;
    }

    /**
     * returns time for format string 00:00
     *
     * @param hourMin
     * @return {@code String}
     */
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
