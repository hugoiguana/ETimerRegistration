package br.com.hugo.etimerregistration.domain;

import lombok.*;

import javax.persistence.*;

/**
 * The {@code Employee} class represents all employees able to register points in the application.
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
@Table(name = "emp_employee")
@AttributeOverride(name = "id", column = @Column(name = "emp_id"))
public class Employee extends PersistEntity {

    /** The employee's name */
    @Column(name = "emp_name", nullable = false)
    private String name;

    /** The employee's PIS(Social Integration Program). */
    @Column(name = "emp_pis", nullable = false, unique = true)
    private String pis;

    /** The employee's password used to log in the application. */
    @Column(name = "emp_password", nullable = false)
    private String password;

    /** The employee's profile. */
    @Column(name = "emp_profile")
    private Integer profile;

    @PrePersist
    public void prePersiste() {
        if (profile == null) {
            profile = EProfile.EMPLOYEER.getCode();
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %s", name, pis);
    }
}
