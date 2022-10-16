package itmo.soa32.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "x", "y", "z"})
@EqualsAndHashCode(of = {"id"})
public class Location {
    private Integer id;
    private float x;
    private Double y;
    private Float z;
}