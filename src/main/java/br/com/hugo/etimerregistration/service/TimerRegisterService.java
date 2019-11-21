package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.repository.TimerRegisterRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimerRegisterService {

    @Getter
    @Autowired
    private TimerRegisterRepository repository;

    
}
