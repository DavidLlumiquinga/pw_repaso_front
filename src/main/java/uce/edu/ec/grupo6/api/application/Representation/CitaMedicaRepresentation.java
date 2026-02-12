package uce.edu.ec.grupo6.api.application.Representation;

import java.time.LocalDate;
import java.time.LocalTime;

import uce.edu.ec.grupo6.api.domain.CitaMedica.EstadoCita;

public class CitaMedicaRepresentation {

    private Long id;
    private Long doctorId;
    private String doctorNombre;
    private String doctorApellido;
    private String doctorEspecialidad;
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteApellido;
    private String pacienteCedula;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivo;
    private EstadoCita estadoCita;

    // Constructores
    public CitaMedicaRepresentation() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorNombre() {
        return doctorNombre;
    }

    public void setDoctorNombre(String doctorNombre) {
        this.doctorNombre = doctorNombre;
    }

    public String getDoctorApellido() {
        return doctorApellido;
    }

    public void setDoctorApellido(String doctorApellido) {
        this.doctorApellido = doctorApellido;
    }

    public String getDoctorEspecialidad() {
        return doctorEspecialidad;
    }

    public void setDoctorEspecialidad(String doctorEspecialidad) {
        this.doctorEspecialidad = doctorEspecialidad;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getPacienteApellido() {
        return pacienteApellido;
    }

    public void setPacienteApellido(String pacienteApellido) {
        this.pacienteApellido = pacienteApellido;
    }

    public String getPacienteCedula() {
        return pacienteCedula;
    }

    public void setPacienteCedula(String pacienteCedula) {
        this.pacienteCedula = pacienteCedula;
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
