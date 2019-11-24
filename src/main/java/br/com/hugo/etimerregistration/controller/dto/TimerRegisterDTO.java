package br.com.hugo.etimerregistration.controller.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimerRegisterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pis;
    private String dateTimePoint;

    @Override
    public String toString() {
        return String.format("%s - %s", pis, dateTimePoint);
    }
}
