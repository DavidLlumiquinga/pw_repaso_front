package uce.edu.ec.grupo6.api.interfaces;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.grupo6.api.application.DoctorService;
import uce.edu.ec.grupo6.api.domain.Doctor;

@Path("/doctores")
@ApplicationScoped
public class DoctorResource {
    @Inject
    private DoctorService doctorService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response registrarDoctor(@Valid Doctor doctor) {
        this.doctorService.guardar(doctor);
        return Response.status(Response.Status.CREATED).entity(doctor).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> obtenerDoctores() {
        return this.doctorService.listarTodos();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response modificarDoctor(@PathParam("id") Long id,@Valid Doctor doctor) {
        Doctor actualizado = doctorService.actualizar(id, doctor);

        if (actualizado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(actualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response borrarDoctor(@PathParam("id") Long id) {
        boolean eliminado = this.doctorService.eliminar(id);

        if (eliminado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
