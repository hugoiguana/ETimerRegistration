package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.domain.TimerRegisterDay;
import br.com.hugo.etimerregistration.util.NumberUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.hugo.etimerregistration.util.DateUtil.*;
import static br.com.hugo.etimerregistration.util.NumberUtil.*;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.time.Duration.between;
import static java.util.Comparator.comparing;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class TimerRegisterDayService {

    public static final BigDecimal HOUR_MIN_TO_REST_WHEN_WORKED_6 = BigDecimal.valueOf(1);
    public static final BigDecimal HOUR_MIN_TO_REST_WHEN_WORKED_4 = BigDecimal.valueOf(0.25);
    public static BigDecimal MIN_TO_PAY_DAY = BigDecimal.valueOf(480).negate();
    private static final BigDecimal TWENTY_PERCENT = BigDecimal.valueOf(0.2);
    private static final BigDecimal FIFTY_PERCENT = BigDecimal.valueOf(0.5);
    private static final BigDecimal ONE_HUNDRED_PERCENT = BigDecimal.valueOf(1);

    public List<TimerRegisterDay> createTimerRegisterDaySheet(final List<TimerRegister> trList, final LocalDate dtIni, final LocalDate dtEnd) {
        List<TimerRegisterDay> trDayList = new ArrayList<>();
        LocalDate dtCurrent = dtIni;
        while (!dtCurrent.isAfter(dtEnd)) {
            final LocalDate dtDay = dtCurrent;
            dtCurrent = dtCurrent.plusDays(NumberUtil.ONE_INT);
            List<LocalDateTime> dtDayPointList = getDateTimePointByDayOrderByDateTimePointAsc(trList, dtDay);
            if (!isWorkingDay(dtDay) && isEmpty(dtDayPointList)) {
                continue;
            }
            trDayList.add(createTimerRegisterDay(dtDayPointList, dtDay));
        }
        return trDayList;
    }

    private TimerRegisterDay createTimerRegisterDay(final List<LocalDateTime> dtDayPointList, final LocalDate dtDay) {
        BigDecimal minWorked = ZERO;
        BigDecimal minRested = ZERO;
        BigDecimal minSpecial = ZERO;

        int i = NumberUtil.ONE_INT;
        if (!isEmpty(dtDayPointList) && dtDayPointList.size() > NumberUtil.ONE_INT) {
            LocalDateTime dtDayPointBefore = dtDayPointList.stream().findFirst().get();
            do {
                LocalDateTime dtDayPointCurrent = dtDayPointList.get(i);
                BigDecimal minBetweenDates = BigDecimal.valueOf(between(dtDayPointBefore, dtDayPointCurrent).toMinutes());
                if (isPair(i)) {
                    minRested = sum(minRested, minBetweenDates);
                } else {
                    minWorked = sum(minWorked, minBetweenDates);
                    minSpecial = sum(minSpecial, addSpecialMinWorked(dtDayPointBefore, dtDayPointCurrent, minWorked));
                }
                dtDayPointBefore = dtDayPointCurrent;
                ++i;
            } while (i < dtDayPointList.size());
        }

        BigDecimal minBalance = add(minWorked, minSpecial);
        if (isWorkingDay(dtDay)) {
            minBalance = add(minBalance, MIN_TO_PAY_DAY);
        }

        BigDecimal hoursWorked = toHour(minWorked);
        BigDecimal hoursRested = toHour(minRested);
        BigDecimal hoursRequiredLeftToRest = calcHoursRequiredLeftToRest(hoursWorked, hoursRested);

        TimerRegisterDay trDay = TimerRegisterDay.builder()
                .hoursWorked(hoursWorked)
                .hoursRested(hoursRested)
                .hoursSpecial(toHour(minSpecial))
                .hoursBalance(toHour(minBalance))
                .hoursRequiredLeftToRest(hoursRequiredLeftToRest)
                .dateDay(dtDay)
                .dateTimePointList(dtDayPointList)
                .build();

        return trDay;
    }

    private BigDecimal addSpecialMinWorked(final LocalDateTime dtDayPointBefore, final LocalDateTime dtDayPointCurrent, final BigDecimal minWorked) {
        BigDecimal minBefore6am = getMinSpecialWorkingDaysBefore6Am(dtDayPointBefore, dtDayPointCurrent);
        BigDecimal minAfter10pm = getMinSpecialWorkingDaysAfter10PM(dtDayPointBefore, dtDayPointCurrent);
        BigDecimal minSaturday = getMinSpecialTimeSaturyday(dtDayPointCurrent, minWorked);
        BigDecimal minSunday = getMinSpecialTimeSunday(dtDayPointCurrent, minWorked);
        return sum(minBefore6am, minAfter10pm, minSaturday, minSunday);
    }

    private BigDecimal getMinSpecialWorkingDaysBefore6Am(final LocalDateTime dtPointBefore, final LocalDateTime dtPointCurrent) {
        BigDecimal minSpecial = ZERO;
        if (isWorkingDay(dtPointCurrent)) {
            LocalDateTime dtMaxSpecialWorkingDays = LocalDateTime.of(dtPointCurrent.toLocalDate(), LocalTime.MIN).withHour(6);
            dtMaxSpecialWorkingDays = dtPointCurrent.isBefore(dtMaxSpecialWorkingDays) ? dtPointCurrent : dtMaxSpecialWorkingDays;
            if (dtPointBefore.isBefore(dtMaxSpecialWorkingDays)) {
                minSpecial = BigDecimal.valueOf(between(dtPointBefore, dtMaxSpecialWorkingDays).toMinutes());
            }
        }
        return minSpecial.multiply(TWENTY_PERCENT);
    }

    private BigDecimal getMinSpecialWorkingDaysAfter10PM(final LocalDateTime dtPointBefore, final LocalDateTime dtPointCurrent) {
        BigDecimal minSpecial = ZERO;
        if (isWorkingDay(dtPointCurrent)) {
            LocalDateTime dtMinSpecial = LocalDateTime.of(dtPointCurrent.toLocalDate(), LocalTime.MIN).withHour(22);
            dtMinSpecial = dtPointBefore.isAfter(dtMinSpecial) ? dtPointBefore : dtMinSpecial;
            if (dtPointCurrent.isAfter(dtMinSpecial)) {
                minSpecial = BigDecimal.valueOf(between(dtMinSpecial, dtPointCurrent).toMinutes());
            }
        }
        return minSpecial.multiply(TWENTY_PERCENT);
    }

    private BigDecimal getMinSpecialTimeSaturyday(final LocalDateTime dtDayPointCurrent, final BigDecimal minWorked) {
        if (isSaturday(dtDayPointCurrent)) {
            return minWorked.multiply(FIFTY_PERCENT);
        }
        return ZERO;
    }

    private BigDecimal getMinSpecialTimeSunday(final LocalDateTime dtDayPointCurrent, final BigDecimal minWorked) {
        if (isSunday(dtDayPointCurrent)) {
            return minWorked.multiply(ONE_HUNDRED_PERCENT);
        }
        return ZERO;
    }

    private List<LocalDateTime> getDateTimePointByDayOrderByDateTimePointAsc(final List<TimerRegister> trList, final LocalDate dtDay) {
        List<LocalDateTime> trDayList = new ArrayList<>();
        if (trList != null && dtDay != null) {
            trDayList = trList.stream()
                    .filter(t -> t.getDateTimePoint().toLocalDate().equals(dtDay))
                    .sorted(comparing(TimerRegister::getDateTimePoint))
                    .map(TimerRegister::getDateTimePoint)
                    .collect(Collectors.toList());
        }
        return trDayList;
    }

    private BigDecimal calcHoursRequiredLeftToRest(BigDecimal hoursWorked, BigDecimal hoursRested) {
        BigDecimal hoursReqLeftToRest = ZERO;
        if (lessThen(hoursRested, ONE)) {
            hoursReqLeftToRest = getHourMinToRest(hoursWorked).subtract(hoursRested);
            if (lessThen(hoursReqLeftToRest, ZERO)) {
                hoursReqLeftToRest = null;
            }
        }
        return hoursReqLeftToRest;
    }

    private BigDecimal getHourMinToRest(BigDecimal hoursWorked) {
        BigDecimal hourMinToRest = ZERO;
        if (biggerThen(hoursWorked, SIX_BIG_D)) {
            hourMinToRest = HOUR_MIN_TO_REST_WHEN_WORKED_6;
        } else if (biggerThen(hoursWorked, FOUR_BIG_D)) {
            hourMinToRest = HOUR_MIN_TO_REST_WHEN_WORKED_4;
        }
        return hourMinToRest;
    }


}
