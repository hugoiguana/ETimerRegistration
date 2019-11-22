package br.com.hugo.etimerregistration.repository;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimerRegisterRepository extends JpaRepository<TimerRegister, Long> {

    Optional<TimerRegister> findFirstByEmployeePisOrderByDateTimePointDesc(String pis);
}