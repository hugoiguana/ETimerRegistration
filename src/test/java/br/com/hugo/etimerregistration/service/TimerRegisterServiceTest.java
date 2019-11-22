package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.repository.TimerRegisterRepository;
import br.com.hugo.etimerregistration.service.exception.AlreadyExistsTimerRegisterInSameMinuteToEmployee;
import br.com.hugo.etimerregistration.service.exception.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class TimerRegisterServiceTest {

    @Mock
    private TimerRegisterRepository repository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    @Spy
    private TimerRegisterService service = new TimerRegisterService();

    private Employee employee;
    private String pis = "123456789";
    private LocalDateTime dateTimePointNow = LocalDateTime.now();
    private TimerRegister timerRegister;

    @Before
    public void before() {
        employee = Employee.builder().name("Hugo").pis("123456789").build();
        employee.setId(Long.valueOf(1));

        timerRegister = TimerRegister.builder().dateTimePoint(dateTimePointNow).build();
        timerRegister.setId(Long.valueOf(1));
    }

    @Test
    public void registerPoint_will_register_point() {
        doReturn(employee).when(employeeService).findByPis(employee.getPis());
        doReturn(FALSE).when(service).existsInSameMinute(pis, dateTimePointNow);
        doReturn(timerRegister).when(repository).save(any(TimerRegister.class));
        TimerRegister trReturned = service.registerPoint(pis, dateTimePointNow);
        assertEquals(trReturned.getId(), timerRegister.getId());
        assertEquals(trReturned.getDateTimePoint(), timerRegister.getDateTimePoint());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void registerPoint_throw_exception_when_pis_not_found() {
        doThrow(new ObjectNotFoundException()).when(employeeService).findByPis(employee.getPis());
        service.registerPoint(employee.getPis(), dateTimePointNow);
    }

    @Test(expected = AlreadyExistsTimerRegisterInSameMinuteToEmployee.class)
    public void registerPoint_throw_exception_when_in_the_same_minute() {
        doReturn(employee).when(employeeService).findByPis(employee.getPis());
        doReturn(true).when(service).existsInSameMinute(pis, dateTimePointNow);
        service.registerPoint(employee.getPis(), dateTimePointNow);
    }

    @Test
    public void existInSameMinute_return_true_when_exists() {
        Optional<TimerRegister> timerRegisterOptional = Optional.of(timerRegister);
        doReturn(timerRegisterOptional).when(repository).findFirstByEmployeePisOrderByDateTimePointDesc(employee.getPis());
        assertTrue(service.existsInSameMinute(employee.getPis(), timerRegister.getDateTimePoint()));
    }

    @Test
    public void existInSameMinute_return_false_when_not_exists() {
        Optional<TimerRegister> timerRegisterOptional = Optional.empty();
        doReturn(timerRegisterOptional).when(repository).findFirstByEmployeePisOrderByDateTimePointDesc(employee.getPis());
        assertFalse(service.existsInSameMinute(employee.getPis(), timerRegister.getDateTimePoint()));
    }

}
