package uce.edu.ec.grupo6.api.intrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.ec.grupo6.api.domain.Doctor;

@ApplicationScoped
public class DoctorRepository implements PanacheRepositoryBase<Doctor,Long>{

}
