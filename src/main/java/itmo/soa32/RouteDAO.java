package itmo.soa32;

//import org.apache.tomcat.util.net.SSLHostConfigCertificate;

import itmo.soa32.dto.LocationDto;
import itmo.soa32.dto.RouteDto;
import itmo.soa32.dto.RoutePostDto;
import itmo.soa32.entities.Route;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class RouteDAO {

    Client client;
    String PORT = "4568";

    public Response findRouteByIds(Integer idFrom, Integer idTo, String order) {
        client = ClientBuilder.newClient();
        String URI = "https://localhost:" + PORT + "/soa-3-1.0-SNAPSHOT/routes?from_id=between:"
                + idFrom + ":" + idFrom + "&to_id=between:" + idTo + ":" + idTo +
                "&sort=" + order;
        List<Route> routes = client.target(URI).request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Route>>(){});
        client.close();
        return Response.ok().entity(routes).build();
    }

    public Response addRouteBetweenLocations(Integer idFrom, Integer idTo, double distance, RouteDto routeDto) {
        RoutePostDto routePost = new RoutePostDto();
        routePost.setDistance(distance);
        routePost.setName(routeDto.getName());
        routePost.setCoordinates(routeDto.getCoordinates());
        routePost.setFrom(new LocationDto(idFrom));
        routePost.setTo(new LocationDto(idTo));
        System.out.println(routePost);
        client = ClientBuilder.newClient();
        String URI = "https://localhost:" + PORT + "/soa-3-1.0-SNAPSHOT/routes";
        Response r = client.target(URI).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(routePost), Response.class);
        client.close();
        return r;
    }
}


