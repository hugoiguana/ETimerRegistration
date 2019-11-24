package br.com.hugo.etimerregistration.service.exception;

import java.time.LocalDateTime;

import static br.com.hugo.etimerregistration.util.DateUtil.DT_FORMAT_DD_MM_YYYY_HH_MM;

public class AlreadyExistsTimerRegisterInSameMinuteToEmployee extends RuntimeException {

    public AlreadyExistsTimerRegisterInSameMinuteToEmployee(LocalDateTime dateTimePoint) {
        super(String.format("Ponto já registrado em %s", dateTimePoint.format(DT_FORMAT_DD_MM_YYYY_HH_MM)));
    }
}
