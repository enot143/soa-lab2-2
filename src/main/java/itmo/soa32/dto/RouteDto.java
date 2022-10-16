package itmo.soa32.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto implements Serializable {
    @NotBlank(message = "name can't be null or empty")
    private String name;
    @NotNull(message = "coordinates can't be null")
    private CoordinateDto coordinates;
}
