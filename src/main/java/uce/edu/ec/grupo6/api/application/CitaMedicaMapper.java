package uce.edu.ec.grupo6.api.application;

import uce.edu.ec.grupo6.api.application.Representation.CitaMedicaRepresentation;
import uce.edu.ec.grupo6.api.domain.CitaMedica;
import uce.edu.ec.grupo6.api.domain.Doctor;
import uce.edu.ec.grupo6.api.domain.Paciente;

public class CitaMedicaMapper {

    public static CitaMedicaRepresentation toRepresentation(CitaMedica cita) {
        if (cita == null) {
            return null;
        }

        CitaMedicaRepresentation repr = new CitaMedicaRepresentation();
        repr.setId(cita.getId());
        repr.setFechaCita(cita.getFechaCita());
        repr.setHoraCita(cita.getHoraCita());
        repr.setMotivo(cita.getMotivo());
        repr.setEstadoCita(cita.getEstadoCita());

        if (cita.getDoctor() != null) {
            repr.setDoctorId(cita.getDoctor().id);
            repr.setDoctorNombre(cita.getDoctor().nombre);
            repr.setDoctorApellido(cita.getDoctor().apellido);
            repr.setDoctorEspecialidad(cita.getDoctor().especialidad);
        }

        if (cita.getPaciente() != null) {
            repr.setPacienteId(cita.getPaciente().getId());
            repr.setPacienteNombre(cita.getPaciente().getNombre());
            repr.setPacienteApellido(cita.getPaciente().getApellido());
            repr.setPacienteCedula(cita.getPaciente().getCedula());
        }

        return repr;
    }

    public static CitaMedica toEntity(CitaMedicaRepresentation repr) {
        if (repr == null) {
            return null;
        }

        CitaMedica cita = new CitaMedica();
        cita.setId(repr.getId());
        cita.setFechaCita(repr.getFechaCita());
        cita.setHoraCita(repr.getHoraCita());
        cita.setMotivo(repr.getMotivo());
        cita.setEstadoCita(repr.getEstadoCita());

        // Crear referencia al doctor (solo con ID)
        if (repr.getDoctorId() != null) {
            Doctor doctor = new Doctor();
            doctor.id = repr.getDoctorId();
            cita.setDoctor(doctor);
        }

        // Crear referencia al paciente (solo con ID)
        if (repr.getPacienteId() != null) {
            Paciente paciente = new Paciente();
            paciente.setId(repr.getPacienteId());
            cita.setPaciente(paciente);
        }

        return cita;
    }
}
