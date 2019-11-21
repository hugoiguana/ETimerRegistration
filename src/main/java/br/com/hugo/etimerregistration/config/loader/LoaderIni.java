package br.com.hugo.etimerregistration.config.loader;
import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static br.com.hugo.etimerregistration.domain.EProfile.EMPLOYEER;

@Profile("dev")
@Configuration
public class LoaderIni implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        insertDefaultEmployee();
    }

    private void insertDefaultEmployee() {
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setName("Hugo");
        employee.setPis("123456789");
        employee.setPassword(passwordEncoder.encode("1234"));
        employee.setProfile(EMPLOYEER.getCode());
        employeeRepository.save(employee);
    }
}
