package orsk.compli.entities.mongo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Document(collection = "global_threats")
public class MongoGlobalThreat {

    @Id
    private ObjectId id;

    @NotBlank
    @Size(max = 150)
    @Indexed
    private String name;

    @Size(max = 1000)
    private String description;

    private LocalDate firstDetected;

    @Min(1)
    @Max(10)
    private int severityLevel;

    private LocalDate dataRetentionUntil;

    private List<MongoVulnerability> vulnerabilities;

    private List<MongoAttackVector> attackVectors;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

