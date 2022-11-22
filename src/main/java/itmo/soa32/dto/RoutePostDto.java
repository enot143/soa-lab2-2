package itmo.soa32.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutePostDto implements Serializable {
    private String name;
    private CoordinateDto coordinates;
    private LocationDto from;
    private LocationDto to;
    private double distance;
}

