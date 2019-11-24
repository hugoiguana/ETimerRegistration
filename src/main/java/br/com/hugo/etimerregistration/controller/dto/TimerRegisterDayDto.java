package br.com.hugo.etimerregistration.controller.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimerRegisterDayDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dateDay;
    private String timeWorked;
    private String timeRest;
    private String timeRequiredLeftToRest;
    private String timeBalance;
    private List<String> pointList = new ArrayList<>();
}
