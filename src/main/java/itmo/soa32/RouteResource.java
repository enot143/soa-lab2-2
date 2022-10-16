package itmo.soa32;

import itmo.soa32.dto.RouteDto;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Path("navigator")
public class RouteResource {
    @EJB
    RouteDAO routeDAO;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("routes/{id-from}/{id-to}/{order-by}")
    public Response findRouteByIds(@PathParam("id-from") Integer idFrom,
                             @PathParam("id-to") Integer idTo,
                             @PathParam("order-by") String order) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        return routeDAO.findRouteByIds(idFrom, idTo, order);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("route/add/{id-from}/{id-to}/{distance}")
    public Response addRouteBetweenLocations(@PathParam("id-from") Integer idFrom,
                                             @PathParam("id-to") Integer idTo,
                                             @PathParam("distance") int distance,
                                             @Valid RouteDto routeDto) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        return routeDAO.addRouteBetweenLocations(idFrom, idTo, distance, routeDto);
    }
}