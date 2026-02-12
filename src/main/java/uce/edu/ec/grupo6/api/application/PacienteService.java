package uce.edu.ec.grupo6.api.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.grupo6.api.application.Representation.PacienteRepresentation;
import uce.edu.ec.grupo6.api.domain.Paciente;
import uce.edu.ec.grupo6.api.intrastructure.PacienteRepository;

@ApplicationScoped
public class PacienteService {

    @Inject
    private PacienteRepository pacienteRepository;

    public List<PacienteRepresentation> listarTodos() {
        List<PacienteRepresentation> list = new ArrayList<>();
        for(Paciente pac : this.pacienteRepository.listAll()) {
            list.add(this.mapperToCR(pac));
        }
        return list;
    }

    public PacienteRepresentation consultarPorId(Integer id){
        return this.mapperToCR(this.pacienteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(PacienteRepresentation pac){
        this.pacienteRepository.persist(this.mapperToPaciente(pac));
    }

    @Transactional
    public void actualizar(Integer id, PacienteRepresentation pac) {
        Paciente pacA = this.mapperToPaciente(this.consultarPorId(id));
        pacA.setCedula(pac.getCedula());
        pacA.setCelular(pac.getCelular());
        pacA.setDireccion(pac.getDireccion());
        pacA.setFecha_nac(pac.getFecha_nac());
        pacA.setApellido(pac.getApellido());
        pacA.setNombre(pac.getNombre());
    }

    @Transactional
    public void actualizarParcial(Integer id, PacienteRepresentation pac) {
        Paciente pacA = this.mapperToPaciente(this.consultarPorId(id));
        if(pac.getCedula() != null) {
            pacA.setCedula(pac.getCedula());
        }
        if(pac.getCelular() != null) {
            pacA.setCelular(pac.getCelular());
        }
        if(pac.getDireccion() != null) {
            pacA.setDireccion(pac.getDireccion());
        }
        if(pac.getFecha_nac() != null) {
            pacA.setFecha_nac(pac.getFecha_nac());
        }
        if(pac.getApellido() != null) {
            pacA.setApellido(pac.getApellido());
        }
        if(pac.getNombre() != null) {
            pacA.setNombre(pac.getNombre());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.pacienteRepository.deleteById(id.longValue());
    }

    public List<PacienteRepresentation> buscarPorCedula(String cedula){
       List<PacienteRepresentation> list = new ArrayList<>();
        for(Paciente pac : this.pacienteRepository.find("cedula = ?1", cedula).list()){
            list.add(this.mapperToCR(pac));
        }
        return list;
    }

    private PacienteRepresentation mapperToCR (Paciente pac) {
        PacienteRepresentation pacR = new PacienteRepresentation();
        pacR.setId(pac.getId());
        pacR.setCedula(pac.getCedula());
        pacR.setCelular(pac.getCelular());
        pacR.setDireccion(pac.getDireccion());
        pacR.setFecha_nac(pac.getFecha_nac());
        pacR.setApellido(pac.getApellido());
        pacR.setNombre(pac.getNombre());
        return pacR;
    }

    private Paciente mapperToPaciente (PacienteRepresentation pacR) {
        Paciente pac = new Paciente();
        pac.setId(pacR.getId());
        pac.setCedula(pacR.getCedula());
        pac.setCelular(pacR.getCelular());
        pac.setDireccion(pacR.getDireccion());
        pac.setFecha_nac(pacR.getFecha_nac());
        pac.setApellido(pacR.getApellido());
        pac.setNombre(pacR.getNombre());
        return pac;
    }

}
