package br.com.hugo.etimerregistration.repository;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The {@code TimerRegisterRepository} interface represents the TimerRegister's repository.
 *
 * @author Hugo Mota
 * @since  1.0
 */
@Repository
public interface TimerRegisterRepository extends JpaRepository<TimerRegister, Long> {

    /**
     * Returns the first TimerRegister {@code Optional} instance found in database by pis and sorted by dateTimePotint desc.
     *
     * @param pis Employee's PIS
     * @return an TimerRegister {@code Optional}
     */
    Optional<TimerRegister> findFirstByEmployeePisOrderByDateTimePointDesc(String pis);

    /**
     * Returns a list of Employee {@code List} found in database by employeeId and DateTimePoint between dateIni and dateEnd.
     *
     * @param employeeId Employee's Id
     * @param dateIni Initial date
     * @param dateEnd Final date
     * @return an Optional of Employee {@code Optional}
     */
    List<TimerRegister> findAllByEmployeeIdAndDateTimePointBetween(Long employeeId, LocalDateTime dateIni, LocalDateTime dateEnd);
}
