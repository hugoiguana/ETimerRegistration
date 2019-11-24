package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.repository.TimerRegisterRepository;
import br.com.hugo.etimerregistration.service.exception.AlreadyExistsTimerRegisterInSameMinuteToEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.hugo.etimerregistration.util.DateUtil.DT_FORMAT_DD_MM_YYYY_HH_MM;

/**
 * The {@code TimerRegisterService} class represents the TimerRegisterService's service.
 *
 * @author Hugo Mota
 * @since  1.0
 */
@Service
public class TimerRegisterService {

    @Autowired
    private TimerRegisterRepository repository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Save a point for an employee.
     *
     * @param pis The Employee's PIS
     * @param dateTimePoint
     * @return {@code TimerRegister}
     */
    @Transactional
    public TimerRegister registerPoint(String pis, LocalDateTime dateTimePoint) {
        Employee employee = employeeService.findByPis(pis);
        if (existsInSameMinute(pis, dateTimePoint)) {
            throw new AlreadyExistsTimerRegisterInSameMinuteToEmployee(dateTimePoint);
        }
        return repository.save(TimerRegister.builder().employee(employee).dateTimePoint(dateTimePoint).build());
    }

    /**
     * Return if a point has already been registered in the same minute for an employee.
     *
     * @param pis The Employee's PIS
     * @param dateTimePoint
     * @return {@code TimerRegister}
     */
    @Transactional(readOnly = true)
    public boolean existsInSameMinute(String pis, LocalDateTime dateTimePoint) {
        Optional<TimerRegister> timerRegister = repository.findFirstByEmployeePisOrderByDateTimePointDesc(pis);
        return timerRegister.filter(t ->
                t.getDateTimePoint().format(DT_FORMAT_DD_MM_YYYY_HH_MM).equals(dateTimePoint.format(DT_FORMAT_DD_MM_YYYY_HH_MM)))
                .isPresent();
    }

}
