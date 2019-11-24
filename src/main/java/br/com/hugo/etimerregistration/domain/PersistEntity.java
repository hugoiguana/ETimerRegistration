package br.com.hugo.etimerregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * The {@code PersistEntity} class represents the generic class used by persistence classes.
 *
 * @author  Hugo Mota
 * @since   1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class PersistEntity {

    /** The code that identifies the employee in the system. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

}
