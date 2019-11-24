package br.com.hugo.etimerregistration.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TimerRegisterDay} class represents information about times register in a day.
 *
 * @author Hugo Mota
 * @since  1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimerRegisterDay {

    /** Day's point register. */
    private LocalDate dateDay;

    /** All points recorded on the day. */
    private List<LocalDateTime> dateTimePointList = new ArrayList<>();

    /** Hours worked on the day. */
    private BigDecimal hoursWorked;

    /** Rested hours in the day. */
    private BigDecimal hoursRested;

    /** Mandatory rest hours to complete */
    private BigDecimal hoursRequiredLeftToRest;

    /**  */
    private BigDecimal hoursSpecial;

    /** Balance of hours in the day. */
    private BigDecimal hoursBalance;
}
