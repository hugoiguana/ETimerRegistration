package br.com.hugo.etimerregistration.controller;

import br.com.hugo.etimerregistration.controller.util.URL;
import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.controller.dto.TimerRegisterDTO;
import br.com.hugo.etimerregistration.controller.dto.TimerResgisterMonthSheetResource;
import br.com.hugo.etimerregistration.service.TimerRegisterMonthService;
import br.com.hugo.etimerregistration.service.TimerRegisterService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.hugo.etimerregistration.util.DateUtil.toLocalDateOrElse;

@RestController
@RequestMapping(value = "/timeregister")
public class TimerRegisterController {

    @Autowired
    private TimerRegisterService service;

    @Autowired
    private TimerRegisterMonthService timerRegisterMonthService;

    @Autowired
    private MapperFacade mapper;

    @PostMapping(value = "{pis}")
    public ResponseEntity<TimerRegisterDTO> insert(@PathVariable String pis) {
        LocalDateTime dateTimePoint = LocalDateTime.now();
        TimerRegister timerRegister = service.registerPoint(pis, dateTimePoint);
        TimerRegisterDTO dto = mapper.map(timerRegister, TimerRegisterDTO.class);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "{pis}")
    public ResponseEntity<TimerResgisterMonthSheetResource> getSheet(@PathVariable String pis
            , @RequestParam(value = "date", defaultValue = "") String dateStr) {
        LocalDate today = LocalDate.now();
        LocalDate period = toLocalDateOrElse(URL.decodeParam(dateStr), today);
        TimerResgisterMonthSheetResource resource = timerRegisterMonthService.createTimerRegisterMonthSheet(pis, period);
        return ResponseEntity.ok().body(resource);
    }
}
