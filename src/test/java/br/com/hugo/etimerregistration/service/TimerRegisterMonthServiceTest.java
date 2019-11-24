package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.controller.dto.TimerRegisterDayDto;
import br.com.hugo.etimerregistration.controller.dto.TimerResgisterMonthSheetResource;
import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.repository.TimerRegisterRepository;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TimerRegisterMonthServiceTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private TimerRegisterRepository timerRegisterRepository;

    @Mock
    private TimerRegisterDayService trDayService;

    @Mock
    private MapperFacade mapper;

    @Spy
    @InjectMocks
    private TimerRegisterMonthService service = new TimerRegisterMonthService();


    @Test
    public void createTimerRegisterMonthSheet() {

        String pis = "123456789";
        LocalDate dateStr = LocalDate.of(2019, 11, 1);
        Employee employee = Employee.builder().name("Hugo").pis(pis).build();
        employee.setId(Long.valueOf(1));

        List<TimerRegister> trList = new ArrayList<>();
        trList.add(TimerRegister.builder().employee(employee)
                .dateTimePoint(LocalDateTime.of(LocalDate.of(2019, 11, 1), LocalTime.of(7, 0, 0))).build());
        trList.add(TimerRegister.builder().employee(employee)
                .dateTimePoint(LocalDateTime.of(LocalDate.of(2019, 11, 1), LocalTime.of(11, 0, 0))).build());
        trList.add(TimerRegister.builder().employee(employee)
                .dateTimePoint(LocalDateTime.of(LocalDate.of(2019, 11, 1), LocalTime.of(12, 0, 0))).build());
        trList.add(TimerRegister.builder().employee(employee)
                .dateTimePoint(LocalDateTime.of(LocalDate.of(2019, 11, 1), LocalTime.of(16, 0, 0))).build());

        List<TimerRegisterDayDto> trDtoList = new ArrayList<>();
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());
        trDtoList.add(TimerRegisterDayDto.builder().build());

        doReturn(employee).when(employeeService).findByPis(pis);
        doReturn(trList).when(timerRegisterRepository).findAllByEmployeeIdAndDateTimePointBetween(any(Long.class), any(LocalDateTime.class), any(LocalDateTime.class));
        doCallRealMethod().when(trDayService).createTimerRegisterDaySheet(anyList(), any(LocalDate.class), any(LocalDate.class));
       //doCallRealMethod().when(trDayService).createTimerRegisterDay(anyList(), any(LocalDate.class));


        doReturn(trDtoList).when(mapper).mapAsList(anyList(), eq(TimerRegisterDayDto.class));

        TimerResgisterMonthSheetResource resource = service.createTimerRegisterMonthSheet(pis, dateStr);

        assertEquals(resource.getTimerRegisterDayList().size(), 17);
        assertEquals(resource.getDtIni(), "01/11/2019");
        assertEquals(resource.getDtEnd(), "30/11/2019");
        assertEquals(resource.getEmployeeName(), employee.getName());
        assertEquals(resource.getEmployeePis(), employee.getPis());
        assertEquals(resource.getTimeWorked(), "08:00");
        assertEquals(resource.getTimeBalance(), "-160:00");
    }

}
