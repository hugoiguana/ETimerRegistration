package br.com.hugo.etimerregistration.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimerRegisterDay {
    private LocalDate dateDay;
    private List<LocalDateTime> dateTimePointList = new ArrayList<>();
    private BigDecimal hoursWorked;
    private BigDecimal hoursRested;
    private BigDecimal hoursRequiredLeftToRest;
    private BigDecimal hoursSpecial;
    private BigDecimal hoursBalance;
}
