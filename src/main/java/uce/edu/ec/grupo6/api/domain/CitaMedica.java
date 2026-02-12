package uce.edu.ec.grupo6.api.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cita_medica")
@SequenceGenerator(name = "cita_seq", sequenceName = "cita_secuencia", allocationSize = 1)
public class CitaMedica extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cita_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id_fk", nullable = false)
    @NotNull(message = "El doctor es obligatorio")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id_fk", nullable = false)
    @NotNull(message = "El paciente es obligatorio")
    private Paciente paciente;

    @NotNull(message = "La fecha de la cita es obligatoria")
    @Column(name = "fecha_cita", nullable = false)
    private LocalDate fechaCita;

    @NotNull(message = "La hora de la cita es obligatoria")
    @Column(name = "hora_cita", nullable = false)
    private LocalTime horaCita;

    @Column(length = 500)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cita", nullable = false)
    @NotNull(message = "El estado de la cita es obligatorio")
    private EstadoCita estadoCita = EstadoCita.CREATED;

    public enum EstadoCita {
        CREATED, // Cita creada
        CANCELLED, // Cita cancelada (borrado lógico)
        COMPLETED // Cita completada
    }

    public void cancelar() {
        if (this.estadoCita == EstadoCita.COMPLETED) {
            throw new IllegalStateException("No se puede cancelar una cita que ya fue completada");
        }
        this.estadoCita = EstadoCita.CANCELLED;
    }

    public void completar() {
        if (this.estadoCita == EstadoCita.CANCELLED) {
            throw new IllegalStateException("No se puede completar una cita que fue cancelada");
        }
        this.estadoCita = EstadoCita.COMPLETED;
    }

    public boolean isActiva() {
        return this.estadoCita != EstadoCita.CANCELLED;
    }

    public void cambiarEstado(EstadoCita nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        this.estadoCita = nuevoEstado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }
}
