package br.com.hugo.etimerregistration.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "emp_employee")
@AttributeOverride(name = "id", column = @Column(name = "emp_id"))
public class Employee extends PersistEntity {

    @Column(name = "emp_name", nullable = false)
    private String name;

    @Column(name = "emp_pis", nullable = false)
    private String pis;

    @Column(name = "emp_password", nullable = false)
    private String password;

    @Column(name = "emp_profile")
    private Integer profile;

    @PrePersist
    public void prePersiste() {
        if (profile == null) {
            profile = EProfile.EMPLOYEER.getCode();
        }
    }

}
