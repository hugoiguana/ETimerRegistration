package br.com.hugo.etimerregistration.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tim_timer_register")
@AttributeOverride(name = "id", column = @Column(name = "tim_id"))
public class TimerRegister extends PersistEntity {

    @Column(nullable = false)
    private LocalDateTime dateTimePoint;

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
