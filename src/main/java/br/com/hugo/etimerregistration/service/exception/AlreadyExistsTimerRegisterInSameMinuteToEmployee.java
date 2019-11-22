package br.com.hugo.etimerregistration.service.exception;

import java.time.LocalDateTime;

import static br.com.hugo.etimerregistration.service.TimerRegisterService.DATE_TIME_FORMAT_HH_MM;

public class AlreadyExistsTimerRegisterInSameMinuteToEmployee extends RuntimeException {

    public AlreadyExistsTimerRegisterInSameMinuteToEmployee(LocalDateTime dateTimePoint) {
        super(String.format("Ponto já registrado em %s", dateTimePoint.format(DATE_TIME_FORMAT_HH_MM)));
    }
}
