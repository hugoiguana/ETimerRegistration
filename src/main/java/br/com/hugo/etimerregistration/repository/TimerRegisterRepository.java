package br.com.hugo.etimerregistration.repository;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimerRegisterRepository extends JpaRepository<TimerRegister, Long> {

}
