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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Document(collection = "campaigns")
public class MongoCampaign {

    @Id
    private ObjectId id;

    @NotBlank
    @Pattern(regexp = "CMP-\\d{6}", message = "Invalid Campaign ID format")
    @Indexed(unique = true)
    private String campaignId;

    @NotBlank
    @Size(max = 200)
    private String name;

    @Size(max = 2000)
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @DBRef
    private List<MongoThreatActor> threatActors;

    @DBRef
    private List<MongoIncident> incidents;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

