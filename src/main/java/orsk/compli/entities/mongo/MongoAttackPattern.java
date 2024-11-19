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
@Document(collection = "attack_patterns")
public class MongoAttackPattern {

    @Id
    private ObjectId id;

    @NotBlank
    @Pattern(regexp = "ATP-\\d{6}", message = "Invalid Attack Pattern ID format")
    @Indexed(unique = true)
    private String patternId;

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 2000)
    private String description;

    @Size(max = 50)
    private String techniqueId;

    @DBRef
    private List<MongoTTP> relatedTTPs;

    @DBRef
    private List<MongoMalware> associatedMalwares;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

