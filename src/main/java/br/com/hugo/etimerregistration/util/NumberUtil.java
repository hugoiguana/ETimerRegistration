package br.com.hugo.etimerregistration.util;

import java.math.BigDecimal;

import static java.util.Arrays.asList;

public class NumberUtil {
    public static final Integer ZERO_INT = 0;
    public static final Integer ONE_INT = 1;
    public static final Integer TWO_INT = 2;

    public static final BigDecimal FOUR_BIG_D = BigDecimal.valueOf(4);
    public static final BigDecimal SIX_BIG_D = BigDecimal.valueOf(6);
    public static final BigDecimal SIXTY_BIG_D = BigDecimal.valueOf(60);


    public static boolean isPair(int number) {
        return number % TWO_INT == ZERO_INT;
    }

    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return num1 != null && num2 != null ? num1.add(num2) : BigDecimal.ZERO;
    }

    public static BigDecimal sum(BigDecimal... minutes) {
        BigDecimal tot = BigDecimal.ZERO;
        if (minutes != null) {
            tot = asList(minutes).stream().reduce(BigDecimal.ZERO, (x, y) -> y == null ? x : x.add(y));
        }
        return tot;
    }

    public static boolean lessThen(BigDecimal num1, BigDecimal num2) {
        return num1 != null && num2 != null && num1.compareTo(num2) < ZERO_INT;
    }

    public static boolean biggerThen(BigDecimal num1, BigDecimal num2) {
        return num1 != null && num2 != null && num1.compareTo(num2) == ONE_INT;
    }
}
