package orsk.compli.entities.mongo;

import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "indicators")
public class MongoIndicator {

    @Id
    private ObjectId id;

    @NotBlank
    @Pattern(regexp = "IND-\\d{6}", message = "Invalid Indicator ID format")
    @Indexed(unique = true)
    private String indicatorId;

    @NotBlank
    @Size(max = 50)
    private String type;

    @NotBlank
    @Size(max = 500)
    private String value;

    @Size(max = 1000)
    private String description;

    private LocalDateTime firstSeen;

    private LocalDateTime lastSeen;

    @DBRef
    private List<MongoIncident> relatedIncidents;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

