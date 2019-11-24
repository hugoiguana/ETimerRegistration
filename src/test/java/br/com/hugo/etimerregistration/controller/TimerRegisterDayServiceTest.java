package br.com.hugo.etimerregistration.controller;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.domain.TimerRegisterDay;
import br.com.hugo.etimerregistration.service.TimerRegisterDayService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimerRegisterDayServiceTest {

    private TimerRegisterDayService service = new TimerRegisterDayService();
    private LocalDate friday = LocalDate.of(2019, 11, 22);
    private List<TimerRegister> trList = new ArrayList<>();

    @Before
    public void setup() {
        trList = new ArrayList<>();
    }


    @Test
    public void testCreateTimerRegisterDaySheet_with_4_points() {
        addTimerRegister(7, 0, 0);
        addTimerRegister(11, 0, 0);
        addTimerRegister(12, 0, 0);
        addTimerRegister(16, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(8)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(1)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(0)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_6_points() {
        addTimerRegister(7, 0, 0);
        addTimerRegister(9, 0, 0);
        addTimerRegister(10, 0, 0);
        addTimerRegister(11, 0, 0);
        addTimerRegister(12, 0, 0);
        addTimerRegister(17, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(8)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(2)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(0)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_0_point() {
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(-8)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_1_point() {
        addTimerRegister(7, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(-8)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_2_points() {
        addTimerRegister(7, 0, 0);
        addTimerRegister(11, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(4)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(-4)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_3_points() {
        addTimerRegister(7, 0, 0);
        addTimerRegister(11, 0, 0);
        addTimerRegister(12, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(4)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(1)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(-4)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_4_points_and_point_after_00h() {
        addTimerRegister(7, 0, 0);
        addTimerRegister(11, 0, 0);
        addTimerRegister(13, 0, 0);
        addTimerRegister(00, 0, 0, friday.plusDays(1));

        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(4)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(2)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(-4)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_4_points_and_point_after_22h() {
        addTimerRegister(7, 0, 0);
        addTimerRegister(11, 0, 0);
        addTimerRegister(13, 0, 0);
        addTimerRegister(23, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(14)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(2)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0.2)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(6.2)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_6_points_and_point_after_22h() {
        addTimerRegister(11, 0, 0);
        addTimerRegister(13, 0, 0);
        addTimerRegister(14, 0, 0);
        addTimerRegister(22, 0, 0);
        addTimerRegister(23, 0, 0);
        addTimerRegister(23, 59, 59);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(10.98)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(2)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0.2)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(3.18)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_4_points_and_point_before_6h() {
        addTimerRegister(4, 0, 0);
        addTimerRegister(11, 0, 0);
        addTimerRegister(12, 0, 0);
        addTimerRegister(16, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(11)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(1)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0.4)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(3.4)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_6_points_and_point_before_6h() {
        addTimerRegister(3, 0, 0);
        addTimerRegister(4, 0, 0);
        addTimerRegister(5, 0, 0);
        addTimerRegister(11, 0, 0);
        addTimerRegister(12, 0, 0);
        addTimerRegister(16, 0, 0);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, friday, friday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(11)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(2)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(0.4)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(3.4)) == 0);
    }


    @Test
    public void testCreateTimerRegisterDaySheet_with_4_points_on_saturday() {
        LocalDate saturday = friday.plusDays(1);
        addTimerRegister(9, 0, 0, saturday);
        addTimerRegister(12, 0, 0, saturday);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, saturday, saturday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(3)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(1.5)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(4.5)) == 0);
    }

    @Test
    public void testCreateTimerRegisterDaySheet_with_4_points_on_sunday() {
        LocalDate sunday = friday.plusDays(2);
        addTimerRegister(9, 0, 0, sunday);
        addTimerRegister(12, 0, 0, sunday);
        List<TimerRegisterDay> trDayList = service.createTimerRegisterDaySheet(trList, sunday, sunday);
        assertTrue(trDayList.get(0).getHoursWorked().compareTo(valueOf(3)) == 0);
        assertTrue(trDayList.get(0).getHoursRested().compareTo(valueOf(0)) == 0);
        assertTrue(trDayList.get(0).getHoursSpecial().compareTo(valueOf(3)) == 0);
        assertTrue(trDayList.get(0).getHoursBalance().compareTo(valueOf(6)) == 0);
    }


    private void addTimerRegister(int h, int m, int s) {
        addTimerRegister(h, m, s, friday);
    }

    private void addTimerRegister(int h, int m, int s, LocalDate data) {
        trList.add(TimerRegister.builder().dateTimePoint(LocalDateTime.of(data, LocalTime.of(h, m, s))).build());
    }

}