package itmo.soa32.controllers;

import itmo.soa32.entities.Route;
import itmo.soa32.services.RouteService;
import itmo.soa32.dto.RouteDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("navigator")
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping(value = "routes/{id-from}/{id-to}/{order-by}")
    public ResponseEntity<List<Route>> findRouteByIds(@PathVariable("id-from") Integer idFrom,
                                                      @PathVariable("id-to") Integer idTo,
                                                      @PathVariable("order-by") String order) {
        return routeService.findRouteByIds(idFrom, idTo, order);
    }

    @PostMapping(value = "route/add/{id-from}/{id-to}/{distance}")
    public ResponseEntity addRouteBetweenLocations(@PathVariable("id-from") Integer idFrom,
                                             @PathVariable("id-to") Integer idTo,
                                             @PathVariable("distance") double distance,
                                             @Valid RouteDto routeDto) {
        return routeService.addRouteBetweenLocations(idFrom, idTo, distance, routeDto);
    }
}