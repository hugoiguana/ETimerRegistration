package br.com.hugo.etimerregistration.util;

import org.junit.Test;

import java.math.BigDecimal;

import static br.com.hugo.etimerregistration.util.NumberUtil.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumberUtilTest {

    @Test
    public void testIsPair_return_true_when_passed_a_pair_number() {
        assertTrue(isPair(2));
    }

    @Test
    public void testIsPair_return_false_when_passed_a_not_pair_number() {
        assertFalse(isPair(1));
    }

    @Test
    public void add_return_num1_plus_num2() {
        assertTrue(add(BigDecimal.valueOf(1), BigDecimal.valueOf(2)).compareTo(BigDecimal.valueOf(3)) == 0);
    }

    @Test
    public void add_return_zero_when_passed_null() {
        assertTrue(add(null, BigDecimal.valueOf(2)).compareTo(BigDecimal.valueOf(0)) == 0);
        assertTrue(add(BigDecimal.valueOf(2), null).compareTo(BigDecimal.valueOf(0)) == 0);
        assertTrue(add(null, null).compareTo(BigDecimal.valueOf(0)) == 0);
    }

    @Test
    public void sum() {
        assertTrue(NumberUtil.sum(null, BigDecimal.valueOf(1), null).compareTo(BigDecimal.valueOf(1)) == 0);
    }

    @Test
    public void lessThen_return_true_when_num1_less_then_num2() {
        assertTrue(lessThen(BigDecimal.valueOf(1), BigDecimal.valueOf(2)));
        assertTrue(lessThen(BigDecimal.valueOf(2), BigDecimal.valueOf(2.1)));
    }

    @Test
    public void lessThen_return_false_when_passed_null() {
        assertFalse(lessThen(null, BigDecimal.valueOf(2)));
        assertFalse(lessThen(BigDecimal.valueOf(2), null));
        assertFalse(lessThen(null, null));
    }

    @Test
    public void biggerThen_return_true_when_num1_bigger_then_num2() {
        assertTrue(biggerThen(BigDecimal.valueOf(2), BigDecimal.valueOf(1)));
        assertTrue(biggerThen(BigDecimal.valueOf(2.1), BigDecimal.valueOf(2)));
    }

    @Test
    public void biggerThen_return_false_when_passed_null() {
        assertFalse(biggerThen(null, BigDecimal.valueOf(2)));
        assertFalse(biggerThen(BigDecimal.valueOf(2), null));
        assertFalse(biggerThen(null, null));
    }

}