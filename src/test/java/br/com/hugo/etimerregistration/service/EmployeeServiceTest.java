package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.repository.EmployeeRepository;
import br.com.hugo.etimerregistration.service.exception.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    @Spy
    private EmployeeService service;

    private Employee employee;

    @Before
    public void before() {
        employee = Employee.builder().name("Hugo").pis("123456789").build();
        employee.setId(Long.valueOf(1));
    }

    @Test
    public void findByPis_return_employee_when_found() {
        Optional<Employee> employeeOptional = Optional.of(employee);
        doReturn(employeeOptional).when(repository).findByPis(employee.getPis());
        Employee employeeReturned = service.findByPis(employee.getPis());
        assertEquals(employee, employeeReturned);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void findByPis__throw_exception_when_not_found() {
        Optional<Employee> employeeOptional = Optional.empty();
        doReturn(employeeOptional).when(repository).findByPis(employee.getPis());
        Employee employeeReturned = service.findByPis(employee.getPis());
        assertEquals(employee, employeeReturned);
    }

}
