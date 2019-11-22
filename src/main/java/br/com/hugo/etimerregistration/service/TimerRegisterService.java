package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.repository.TimerRegisterRepository;
import br.com.hugo.etimerregistration.service.exception.AlreadyExistsTimerRegisterInSameMinuteToEmployee;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class TimerRegisterService {

    public static DateTimeFormatter DATE_TIME_FORMAT_HH_MM = ofPattern("dd/MM/yyyy hh:mm");
    public static DateTimeFormatter DATE_TIME_FORMAT_HH_MM_SS = ofPattern("dd/MM/yyyy hh:mm:ss");

    @Getter
    @Autowired
    private TimerRegisterRepository repository;

    @Autowired
    private EmployeeService employeeService;

    @Transactional
    public TimerRegister registerPoint(String pis, LocalDateTime dateTimePoint) {
        Employee employee = employeeService.findByPis(pis);
        if (existsInSameMinute(pis, dateTimePoint)) {
            throw new AlreadyExistsTimerRegisterInSameMinuteToEmployee(dateTimePoint);
        }
        return repository.save(TimerRegister.builder().employee(employee).dateTimePoint(dateTimePoint).build());
    }

    @Transactional(readOnly = true)
    public boolean existsInSameMinute(String pis, LocalDateTime dateTimePoint) {
        Optional<TimerRegister> timerRegister = repository.findFirstByEmployeePisOrderByDateTimePointDesc(pis);
        return timerRegister.filter(t ->
                t.getDateTimePoint().format(DATE_TIME_FORMAT_HH_MM).equals(dateTimePoint.format(DATE_TIME_FORMAT_HH_MM)))
                .isPresent();
    }
}
