package br.com.hugo.etimerregistration.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The {@code TimerRegister} class is a persistence class that represents the register time of employees.
 *
 * @author  Hugo Mota
 * @since   1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tim_timer_register")
@AttributeOverride(name = "id", column = @Column(name = "tim_id"))
public class TimerRegister extends PersistEntity {

    /** Time of the point registration. */
    @Column(nullable = false)
    private LocalDateTime dateTimePoint;

    /** Employee that register the point. */
    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @PrePersist
    public void prePersist() {
        if (dateTimePoint == null) {
            dateTimePoint = LocalDateTime.now();
        }
    }
}
