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
public class TimerResgisterMonthSheetResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dtIni;
    private String dtEnd;
    private String employeeName;
    private String employeePis;
    private String timeWorked;
    private String timeBalance;
    private List<TimerRegisterDayDto> timerRegisterDayList = new ArrayList<>();

    public void addTimerRegisterDay(TimerRegisterDayDto trd) {
        if (timerRegisterDayList == null) {
            timerRegisterDayList = new ArrayList<>();
        }
        timerRegisterDayList.add(trd);
    }
}
