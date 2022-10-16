package itmo.soa32.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "x", "y"})
@EqualsAndHashCode(of = {"id"})
public class Coordinate {
    private Integer id;
    private double x;
    private Float y;
}