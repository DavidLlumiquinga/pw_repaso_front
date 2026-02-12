package uce.edu.ec.grupo6.api.interfaces;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.grupo6.api.application.PacienteService;
import uce.edu.ec.grupo6.api.application.Representation.PacienteRepresentation;

@Path("/pacientes")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @Inject
    private PacienteService pacienteService;

    @GET
    @Path("")
    @RolesAllowed("admin")
    public List<PacienteRepresentation> listarTodos() {
        List<PacienteRepresentation> pac = this.pacienteService.listarTodos();
        System.out.println(pac);
        return pac;
    }

    @GET
    @Path("cedula")
    @RolesAllowed("admin")
    public List<PacienteRepresentation> buscarPorCedula(@QueryParam("cedula") String cedula) {
        System.out.println("LISTAR POR CEDULA");
        return this.pacienteService.buscarPorCedula(cedula);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    public PacienteRepresentation consultarPorId(@PathParam("id") Integer iden) {
        return this.pacienteService.consultarPorId(iden);
    }

    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response guardar(PacienteRepresentation pac) {
        this.pacienteService.crear(pac);
        return Response.status(Response.Status.CREATED).entity(pac).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer id, PacienteRepresentation pac) {
        this.pacienteService.actualizar(id, pac);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response actualizarParcial(@PathParam("id") Integer id, PacienteRepresentation pac) {
        this.pacienteService.actualizarParcial(id, pac);
        return Response.status(209).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void borrar(@PathParam("id") Integer id) {
        this.pacienteService.eliminar(id);
        ;
    }
}
