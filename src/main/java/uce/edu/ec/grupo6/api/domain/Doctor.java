package uce.edu.ec.grupo6.api.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; 

@Entity
@Table(name = "doctor")
@SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_secuencia", allocationSize = 1)
public class Doctor extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    public Long id;

    @NotBlank(message = "El nombre es obligatorio") 
    @Column(length = 100, nullable = false)
    public String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(length = 100, nullable = false)
    public String apellido;

    @Column(length = 50)
    public String especialidad;

    @NotBlank(message = "La licencia médica es obligatoria")
    @Column(length = 20, unique = true, nullable = false)
    public String nro_licencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    public EstadoDoctor estado; 

    public enum EstadoDoctor {
        ACTIVO, INACTIVO
    }
}