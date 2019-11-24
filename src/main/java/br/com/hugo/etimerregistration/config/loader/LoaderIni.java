package br.com.hugo.etimerregistration.config.loader;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.repository.EmployeeRepository;
import br.com.hugo.etimerregistration.repository.TimerRegisterRepository;
import br.com.hugo.etimerregistration.service.TimerRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static br.com.hugo.etimerregistration.domain.EProfile.EMPLOYEER;
import static java.util.Arrays.asList;

@Profile("dev")
@Configuration
public class LoaderIni implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimerRegisterRepository timerRegisterRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Employee employee;

    @Override
    public void run(String... args) throws Exception {
        insertDefaultEmployee();
        insertTimerRegister();
    }

    private void insertDefaultEmployee() {
        employee = new Employee();
        employee.setId(1l);
        employee.setName("Hugo");
        employee.setPis("123456789");
        employee.setPassword(passwordEncoder.encode("1234"));
        employee.setProfile(EMPLOYEER.getCode());
        employeeRepository.save(employee);
    }

    private void insertTimerRegister() {
        LocalDate day1 = LocalDate.now().withDayOfMonth(1);
        LocalDate day2 = day1.plusDays(1);
        LocalDate day3 = day2.plusDays(1);
        LocalDate day4 = day3.plusDays(1);
        LocalDate day5 = day4.plusDays(1);
        LocalDate day6 = day5.plusDays(1);
        LocalDate day7 = day6.plusDays(1);
        LocalDate day8 = day7.plusDays(1);

        List<TimerRegister> trList = asList(TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day1, LocalTime.of(7, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day1, LocalTime.of(11, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day1, LocalTime.of(12, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day1, LocalTime.of(16, 0, 0))).build(),

                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day2, LocalTime.of(7, 0, 0))).build(),

                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day3, LocalTime.of(7, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day3, LocalTime.of(10, 0, 0))).build(),

                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day4, LocalTime.of(7, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day4, LocalTime.of(11, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day4, LocalTime.of(12, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day4, LocalTime.of(16, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day4, LocalTime.of(17, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day4, LocalTime.of(20, 0, 0))).build(),

                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day5, LocalTime.of(7, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day5, LocalTime.of(11, 0, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day5, LocalTime.of(12, 0, 0))).build(),


                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day6, LocalTime.of(7, 35, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day6, LocalTime.of(11, 20, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day6, LocalTime.of(12, 40, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day6, LocalTime.of(16, 45, 0))).build(),

                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day7, LocalTime.of(7, 35, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day7, LocalTime.of(14, 20, 0))).build(),

                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day8, LocalTime.of(7, 35, 0))).build(),
                TimerRegister.builder().employee(employee).dateTimePoint(LocalDateTime.of(day8, LocalTime.of(9, 20, 0))).build());

        timerRegisterRepository.saveAll(trList);
    }
}
