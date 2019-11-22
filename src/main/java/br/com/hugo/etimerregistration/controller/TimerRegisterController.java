package br.com.hugo.etimerregistration.controller;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.dto.TimerRegisterDTO;
import br.com.hugo.etimerregistration.service.TimerRegisterService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/timeregister")
public class TimerRegisterController {

    @Autowired
    private TimerRegisterService service;

    @Autowired
    private MapperFacade mapper;

    @PostMapping(value = "{pis}")
    public ResponseEntity<TimerRegisterDTO> insert(@PathVariable String pis) {
        LocalDateTime dateTimePoint = LocalDateTime.now();
        TimerRegister timerRegister = service.registerPoint(pis, dateTimePoint);
        TimerRegisterDTO dto = mapper.map(timerRegister, TimerRegisterDTO.class);
        return ResponseEntity.ok().body(dto);
    }

}
