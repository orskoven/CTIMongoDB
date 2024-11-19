package orsk.compli.entities.mongo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Document(collection = "incidents")
public class MongoIncident {

    @Id
    private ObjectId id;

    @NotBlank
    @Pattern(regexp = "INC-\\d{6}", message = "Invalid Incident ID format")
    @Indexed(unique = true)
    private String incidentId;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 2000)
    private String description;

    @NotNull
    private LocalDateTime reportedAt;

    @NotBlank
    @Size(max = 50)
    private String status;

    @DBRef
    private List<MongoThreatActor> threatActors;

    @DBRef
    private List<MongoAffectedProduct> affectedProducts;

    private MongoGeolocation geolocation;

    @DBRef
    private List<MongoMalware> malwareUsed;

    @DBRef
    private List<MongoIndicator> indicators;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

