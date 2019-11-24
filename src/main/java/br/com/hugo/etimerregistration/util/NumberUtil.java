package br.com.hugo.etimerregistration.util;

import java.math.BigDecimal;

import static java.util.Arrays.asList;

/**
 * Utility class for number.
 *
 * @author Hugo Mota
 * @since  1.0
 */
public class NumberUtil {

    /** Number zero as Integer */
    public static final Integer ZERO_INT = 0;

    /** Number one as Integer */
    public static final Integer ONE_INT = 1;

    /** Number two as Integer */
    public static final Integer TWO_INT = 2;

    /** Number four as BigDecimal */
    public static final BigDecimal FOUR_BIG_D = BigDecimal.valueOf(4);

    /** Number six as BigDecimal */
    public static final BigDecimal SIX_BIG_D = BigDecimal.valueOf(6);

    /** Number sixty as BigDecimal */
    public static final BigDecimal SIXTY_BIG_D = BigDecimal.valueOf(60);

    /**
     * Returns if a number is pair.
     *
     * @param number
     * @return {@code boolean}
     */
    public static boolean isPair(int number) {
        return number % TWO_INT == ZERO_INT;
    }

    /**
     * add two numbers if they are not null.
     *
     * @param num1
     * @param num2
     * @return {@code boolean}
     */
    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return num1 != null && num2 != null ? num1.add(num2) : BigDecimal.ZERO;
    }

    /**
     * add a list of numbers if they are not null.
     *
     * @param minutes
     * @return {@code boolean}
     */
    public static BigDecimal sum(BigDecimal... minutes) {
        BigDecimal tot = BigDecimal.ZERO;
        if (minutes != null) {
            tot = asList(minutes).stream().reduce(BigDecimal.ZERO, (x, y) -> y == null ? x : x.add(y));
        }
        return tot;
    }

    /**
     * Returns if num1 is less then num2.
     *
     * @param num1
     * @param num2
     * @return {@code boolean}
     */
    public static boolean lessThen(BigDecimal num1, BigDecimal num2) {
        return num1 != null && num2 != null && num1.compareTo(num2) < ZERO_INT;
    }

    /**
     * Returns if num1 is bigger then num2.
     *
     * @param num1
     * @param num2
     * @return {@code boolean}
     */
    public static boolean biggerThen(BigDecimal num1, BigDecimal num2) {
        return num1 != null && num2 != null && num1.compareTo(num2) == ONE_INT;
    }
}
