package uce.edu.ec.grupo6.api.interfaces;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
import uce.edu.ec.grupo6.api.application.CitaMedicaService;
import uce.edu.ec.grupo6.api.domain.CitaMedica;
import uce.edu.ec.grupo6.api.domain.CitaMedica.EstadoCita;

@Path("/citas")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CitaMedicaResource {

    @Inject
    private CitaMedicaService citaMedicaService;

    @POST
    @Path("")
    @RolesAllowed("admin")
    public Response crear(@Valid CitaMedica citaMedica) {
        try {
            CitaMedica citaCreada = citaMedicaService.crear(citaMedica);
            return Response.status(Response.Status.CREATED).entity(citaCreada).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("")
    public Response listarTodas(@QueryParam("soloActivas") boolean soloActivas) {
        List<CitaMedica> citas;
        if (soloActivas) {
            citas = citaMedicaService.listarActivas();
        } else {
            citas = citaMedicaService.listarTodas();
        }
        return Response.ok(citas).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            CitaMedica cita = citaMedicaService.buscarPorId(id);
            return Response.ok(cita).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/doctor/{doctorId}")
    public Response listarPorDoctor(@PathParam("doctorId") Long doctorId) {
        List<CitaMedica> citas = citaMedicaService.listarPorDoctor(doctorId);
        return Response.ok(citas).build();
    }

    @GET
    @Path("/paciente/{pacienteId}")
    public Response listarPorPaciente(@PathParam("pacienteId") Long pacienteId) {
        List<CitaMedica> citas = citaMedicaService.listarPorPaciente(pacienteId);
        return Response.ok(citas).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Long id, @Valid CitaMedica citaMedica) {
        try {
            CitaMedica citaActualizada = citaMedicaService.actualizar(id, citaMedica);
            return Response.ok(citaActualizada).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @PATCH
    @Path("/{id}/cancelar")
    @RolesAllowed("admin")
    public Response cancelar(@PathParam("id") Long id) {
        try {
            CitaMedica citaCancelada = citaMedicaService.cancelar(id);
            return Response.ok(citaCancelada).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @PATCH
    @Path("/{id}/completar")
    @RolesAllowed("admin")
    public Response completar(@PathParam("id") Long id) {
        try {
            CitaMedica citaCompletada = citaMedicaService.completar(id);
            return Response.ok(citaCompletada).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @PATCH
    @Path("/{id}/estado")
    @RolesAllowed("admin")
    public Response cambiarEstado(@PathParam("id") Long id, @QueryParam("estado") EstadoCita estado) {
        try {
            CitaMedica citaActualizada = citaMedicaService.cambiarEstado(id, estado);
            return Response.ok(citaActualizada).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response eliminarFisicamente(@PathParam("id") Long id) {
        boolean eliminado = citaMedicaService.eliminarFisicamente(id);
        if (eliminado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Cita no encontrada"))
                    .build();
        }
    }

    public static class ErrorResponse {
        public String mensaje;

        public ErrorResponse() {
        }

        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
