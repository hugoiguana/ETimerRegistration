package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.domain.TimerRegisterDay;
import br.com.hugo.etimerregistration.controller.dto.TimerRegisterDayDto;
import br.com.hugo.etimerregistration.controller.dto.TimerResgisterMonthSheetResource;
import br.com.hugo.etimerregistration.repository.TimerRegisterRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static br.com.hugo.etimerregistration.util.DateUtil.DT_FORMAT_DD_MM_YYYY;
import static br.com.hugo.etimerregistration.util.DateUtil.toHourMinunteString;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class TimerRegisterMonthService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TimerRegisterRepository timerRegisterRepository;

    @Autowired
    private TimerRegisterDayService trDayService;

    @Autowired
    private MapperFacade mapper;


    @Transactional(readOnly = true)
    public TimerResgisterMonthSheetResource createTimerRegisterMonthSheet(final String pis, final LocalDate dateStr) {

        Employee employee = employeeService.findByPis(pis);
        LocalDateTime dtIni = LocalDateTime.of(dateStr, LocalTime.MIN).with(firstDayOfMonth());
        LocalDateTime dtEnd = LocalDateTime.of(dateStr, LocalTime.MAX).with(lastDayOfMonth());

        List<TimerRegister> trList = timerRegisterRepository.findAllByEmployeeIdAndDateTimePointBetween(employee.getId(), dtIni, dtEnd);
        List<TimerRegisterDay> trDayList = trDayService.createTimerRegisterDaySheet(trList, dtIni.toLocalDate(), dtEnd.toLocalDate());

        BigDecimal hoursBalanceMonth = trDayList.stream().map(TimerRegisterDay::getHoursBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal hoursWorkedMonth = trDayList.stream().map(TimerRegisterDay::getHoursWorked).reduce(BigDecimal.ZERO, BigDecimal::add);

        List<TimerRegisterDayDto> trDayDtoList = mapper.mapAsList(trDayList, TimerRegisterDayDto.class);

        TimerResgisterMonthSheetResource resource = TimerResgisterMonthSheetResource.builder()
                .dtIni(dtIni.format(DT_FORMAT_DD_MM_YYYY))
                .dtEnd(dtEnd.format(DT_FORMAT_DD_MM_YYYY))
                .employeeName(employee.getName())
                .employeePis(pis)
                .timeBalance(toHourMinunteString(hoursBalanceMonth))
                .timeWorked(toHourMinunteString(hoursWorkedMonth))
                .timerRegisterDayList(trDayDtoList)
                .build();

        return resource;
    }

}
