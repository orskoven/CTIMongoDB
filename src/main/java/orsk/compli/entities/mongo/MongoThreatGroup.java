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
@Document(collection = "threat_groups")
public class MongoThreatGroup {

    @Id
    private ObjectId id;

    @NotBlank
    @Pattern(regexp = "GRP-\\d{6}", message = "Invalid Group ID format")
    @Indexed(unique = true)
    private String groupId;

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 2000)
    private String description;

    @Size(max = 10)
    private List<String> knownAliases;

    @DBRef
    private List<MongoThreatActor> associatedThreatActors;

    @DBRef
    private List<MongoCampaign> campaigns;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

