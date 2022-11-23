package itmo.soa32.services;

import itmo.soa32.dto.LocationDto;
import itmo.soa32.dto.RouteDto;
import itmo.soa32.dto.RoutePostDto;
import itmo.soa32.entities.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RouteService {
    public static final String BASE_URL = "http://localhost:4567/soa-3-1.0-SNAPSHOT";

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<List<Route>> findRouteByIds(Integer idFrom, Integer idTo, String order) {
        String URI = BASE_URL + "/routes?from_id=between:"
                + idFrom + ":" + idFrom + "&to_id=between:" + idTo + ":" + idTo +
                "&sort=" + order;
        List<Route> routes = restTemplate.getForObject(URI, List.class);
        return ResponseEntity.ok(routes);
    }

    public ResponseEntity addRouteBetweenLocations(Integer idFrom, Integer idTo, double distance, RouteDto routeDto) {
        System.out.println(routeDto.toString());
        RoutePostDto routePost = new RoutePostDto();
        routePost.setDistance(distance);
        routePost.setName(routeDto.getName());
        routePost.setCoordinates(routeDto.getCoordinates());
        routePost.setFrom(new LocationDto(idFrom));
        routePost.setTo(new LocationDto(idTo));
        System.out.println(routePost);
        String URI = BASE_URL + "/routes";
        return restTemplate.postForEntity(URI, routePost, ResponseEntity.class);
    }
}


