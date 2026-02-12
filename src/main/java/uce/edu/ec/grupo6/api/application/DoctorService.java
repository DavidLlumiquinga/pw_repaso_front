package uce.edu.ec.grupo6.api.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.grupo6.api.domain.Doctor;
import uce.edu.ec.grupo6.api.intrastructure.DoctorRepository;

@ApplicationScoped
public class DoctorService {
    @Inject
    private DoctorRepository doctorRepository;

    // Create
    @Transactional
    public void guardar(Doctor doctor) {
        this.doctorRepository.persist(doctor);
    }

    // Read
    public List<Doctor> listarTodos() {
        return this.doctorRepository.listAll();
    }

    // Update
    @Transactional
    public Doctor actualizar(Long id, Doctor doctor) {
        Doctor doc = doctorRepository.findById(id);

        if (doc == null) {
            return null;
        }

        doc.nombre = doctor.nombre;
        doc.apellido = doctor.apellido;
        doc.especialidad = doctor.especialidad;
        doc.nro_licencia = doctor.nro_licencia;
        doc.estado = doctor.estado;

        return doc;
    }
    // Delete

    @Transactional
    public boolean eliminar(Long id) {
        return this.doctorRepository.deleteById(id);
    }
}
