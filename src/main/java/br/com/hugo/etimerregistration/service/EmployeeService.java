package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.repository.EmployeeRepository;
import br.com.hugo.etimerregistration.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

/**
 * The {@code EmployeeService} class represents the Employee's service.
 *
 * @author Hugo Mota
 * @since  1.0
 */
@Service
public class EmployeeService {

    /** Message information about employee not found. */
    public static final String MSG_ERRO_EMPLOYEE_NOT_FOUND = "Funcionário com pis %s não encontrado";

    @Autowired
    private EmployeeRepository repository;

    /**
     * Returns an Employee {@code Employee} instance found in database by pis.
     *
     * If no employee is found an exception {@code ObjectNotFoundException} will be thrown.
     *
     * @param pis The Employee's PIS
     * @return an Employee {@code Employee}
     */
    public Employee findByPis(String pis) {
        Optional<Employee> employee = repository.findByPis(pis);
        return employee.orElseThrow(() -> new ObjectNotFoundException(format(MSG_ERRO_EMPLOYEE_NOT_FOUND, pis)));
    }
}
