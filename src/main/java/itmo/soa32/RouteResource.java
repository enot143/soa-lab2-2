package itmo.soa32;

import itmo.soa32.dto.RouteDto;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("navigator")
public class RouteResource {
    @EJB
    RouteDAO routeDAO;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("routes/{id-from}/{id-to}/{order-by}")
    public Response findRouteByIds(@PathParam("id-from") Integer idFrom,
                             @PathParam("id-to") Integer idTo,
                             @PathParam("order-by") String order) {
        return routeDAO.findRouteByIds(idFrom, idTo, order);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("route/add/{id-from}/{id-to}/{distance}")
    public Response addRouteBetweenLocations(@PathParam("id-from") Integer idFrom,
                                             @PathParam("id-to") Integer idTo,
                                             @PathParam("distance") double distance,
                                             @Valid RouteDto routeDto) {
        return routeDAO.addRouteBetweenLocations(idFrom, idTo, distance, routeDto);
    }
}