package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.repository.EmployeeRepository;
import br.com.hugo.etimerregistration.service.exception.ObjectNotFoundException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
public class EmployeeService {

    public static final String MSG_ERRO_EMPLOYEE_NOT_FOUND = "Funcionário com pis %s não encontrado";

    @Autowired
    private EmployeeRepository repository;

    public Employee findByPis(String pis) {
        Optional<Employee> employee = repository.findByPis(pis);
        return employee.orElseThrow(() -> new ObjectNotFoundException(format(MSG_ERRO_EMPLOYEE_NOT_FOUND, pis)));
    }
}
