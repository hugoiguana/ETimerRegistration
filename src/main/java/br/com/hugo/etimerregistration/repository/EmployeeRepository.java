package br.com.hugo.etimerregistration.repository;

import br.com.hugo.etimerregistration.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The {@code EmployeeRepository} interface represents the Employee's repository.
 *
 * @author Hugo Mota
 * @since  1.0
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Returns an Employee {@code Optional} instance found in database by pis.
     *
     * @param pis The Employee's PIS
     * @return an Employee {@code Optional}
     */
    Optional<Employee> findByPis(String pis);

}
