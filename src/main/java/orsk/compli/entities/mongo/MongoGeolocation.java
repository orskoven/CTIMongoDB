package orsk.compli.entities.mongo;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document("geolocations")
public class MongoGeolocation {

    @NotBlank
    @Pattern(regexp = "^((25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.|$)){4}$", message = "Invalid IP address format")
    @Indexed
    private String ipAddress;

    @Size(max = 100)
    private String region;

    @Size(max = 100)
    private String city;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double longitude;

}

