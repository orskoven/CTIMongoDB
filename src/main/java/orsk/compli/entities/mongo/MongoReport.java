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
@Document(collection = "reports")
public class MongoReport {

    @Id
    private ObjectId id;

    @NotBlank
    @Pattern(regexp = "RPT-\\d{6}", message = "Invalid Report ID format")
    @Indexed(unique = true)
    private String reportId;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime publishedAt;

    @Size(max = 100)
    private String author;

    @DBRef
    private List<MongoIncident> relatedIncidents;

    @DBRef
    private List<MongoThreatActor> relatedThreatActors;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

