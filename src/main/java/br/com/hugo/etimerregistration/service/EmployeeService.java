package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.repository.EmployeeRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    @Getter
    private EmployeeRepository repository;

}
