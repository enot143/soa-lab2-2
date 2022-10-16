package itmo.soa32.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString(of = {"id", "name", "coordinates", "creationDate", "from", "to", "distance"})
@EqualsAndHashCode(of = {"id"})
public class Route {
    private Integer id;
    private String name;
    private Coordinate coordinates;
    private LocalDate creationDate;
    private Location from;
    private Location to;
    private int distance;
}