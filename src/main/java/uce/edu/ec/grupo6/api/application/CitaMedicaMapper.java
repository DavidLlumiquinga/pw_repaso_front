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
        repr.setId(cita.id);
        repr.setFechaCita(cita.fechaCita);
        repr.setHoraCita(cita.horaCita);
        repr.setMotivo(cita.motivo);
        repr.setEstadoCita(cita.estadoCita);

        if (cita.doctor != null) {
            repr.setDoctorId(cita.doctor.id);
            repr.setDoctorNombre(cita.doctor.nombre);
            repr.setDoctorApellido(cita.doctor.apellido);
            repr.setDoctorEspecialidad(cita.doctor.especialidad);
        }

        if (cita.paciente != null) {
            repr.setPacienteId(cita.paciente.id);
            repr.setPacienteNombre(cita.paciente.nombre);
            repr.setPacienteApellido(cita.paciente.apellido);
            repr.setPacienteCedula(cita.paciente.cedula);
        }

        return repr;
    }

    public static CitaMedica toEntity(CitaMedicaRepresentation repr) {
        if (repr == null) {
            return null;
        }

        CitaMedica cita = new CitaMedica();
        cita.id = repr.getId();
        cita.fechaCita = repr.getFechaCita();
        cita.horaCita = repr.getHoraCita();
        cita.motivo = repr.getMotivo();
        cita.estadoCita = repr.getEstadoCita();

        // Crear referencia al doctor (solo con ID)
        if (repr.getDoctorId() != null) {
            Doctor doctor = new Doctor();
            doctor.id = repr.getDoctorId();
            cita.doctor = doctor;
        }

        // Crear referencia al paciente (solo con ID)
        if (repr.getPacienteId() != null) {
            Paciente paciente = new Paciente();
            paciente.id = repr.getPacienteId();
            cita.paciente = paciente;
        }

        return cita;
    }
}
