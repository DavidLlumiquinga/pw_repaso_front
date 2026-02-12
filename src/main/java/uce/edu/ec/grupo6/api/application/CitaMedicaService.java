package uce.edu.ec.grupo6.api.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import uce.edu.ec.grupo6.api.domain.CitaMedica;
import uce.edu.ec.grupo6.api.domain.CitaMedica.EstadoCita;
import uce.edu.ec.grupo6.api.domain.Doctor;
import uce.edu.ec.grupo6.api.domain.Paciente;
import uce.edu.ec.grupo6.api.intrastructure.CitaMedicaRepository;
import uce.edu.ec.grupo6.api.intrastructure.DoctorRepository;
import uce.edu.ec.grupo6.api.intrastructure.PacienteRepository;

@ApplicationScoped
public class CitaMedicaService {

    @Inject
    private CitaMedicaRepository citaMedicaRepository;

    @Inject
    private DoctorRepository doctorRepository;

    @Inject
    private PacienteRepository pacienteRepository;

    @Transactional
    public CitaMedica crear(CitaMedica citaMedica) {
        // Validar que el doctor existe
        Doctor doctor = doctorRepository.findById(citaMedica.getDoctor().id);
        if (doctor == null) {
            throw new NotFoundException("Doctor no encontrado con ID: " + citaMedica.getDoctor().id);
        }

        // Validar que el paciente existe
        Paciente paciente = pacienteRepository.findById(citaMedica.getPaciente().id);
        if (paciente == null) {
            throw new NotFoundException("Paciente no encontrado con ID: " + citaMedica.getPaciente().id);
        }

        // Establecer las entidades completas
        citaMedica.setDoctor(doctor);
        citaMedica.setPaciente(paciente);

        // El estado CREATED se establece automáticamente en @PrePersist
        citaMedicaRepository.persist(citaMedica);
        return citaMedica;
    }

    public List<CitaMedica> listarTodas() {
        return citaMedicaRepository.listAll();
    }

    public List<CitaMedica> listarActivas() {
        return citaMedicaRepository.list("estadoCita != ?1", EstadoCita.CANCELLED);
    }

    public CitaMedica buscarPorId(Long id) {
        CitaMedica cita = citaMedicaRepository.findById(id);
        if (cita == null) {
            throw new NotFoundException("Cita médica no encontrada con ID: " + id);
        }
        return cita;
    }

    public List<CitaMedica> listarPorDoctor(Long doctorId) {
        return citaMedicaRepository.list("doctor.id", doctorId);
    }

    public List<CitaMedica> listarPorPaciente(Long pacienteId) {
        return citaMedicaRepository.list("paciente.id", pacienteId);
    }

    @Transactional
    public CitaMedica cancelar(Long id) {
        CitaMedica cita = buscarPorId(id);
        cita.cancelar(); // Utiliza el método de la entidad
        return cita;
    }

    @Transactional
    public CitaMedica completar(Long id) {
        CitaMedica cita = buscarPorId(id);
        cita.completar(); // Utiliza el método de la entidad
        return cita;
    }

    @Transactional
    public CitaMedica cambiarEstado(Long id, EstadoCita nuevoEstado) {
        CitaMedica cita = buscarPorId(id);
        cita.cambiarEstado(nuevoEstado);
        return cita;
    }

    @Transactional
    public CitaMedica actualizar(Long id, CitaMedica citaActualizada) {
        CitaMedica cita = buscarPorId(id);

        // Solo actualizar si la cita no está cancelada
        if (cita.getEstadoCita() == EstadoCita.CANCELLED) {
            throw new IllegalStateException("No se puede actualizar una cita cancelada");
        }

        // Actualizar campos
        if (citaActualizada.getFechaCita() != null) {
            cita.setFechaCita(citaActualizada.getFechaCita());
        }
        if (citaActualizada.getHoraCita() != null) {
            cita.setHoraCita(citaActualizada.getHoraCita());
        }
        if (citaActualizada.getMotivo() != null) {
            cita.setMotivo(citaActualizada.getMotivo());
        }

        return cita;
    }

    @Transactional
    public boolean eliminarFisicamente(Long id) {
        return citaMedicaRepository.deleteById(id);
    }
}
