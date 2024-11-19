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
@Document(collection = "ttps")
public class MongoTTP {

    @Id
    private ObjectId id;

    @NotBlank
    @Pattern(regexp = "TTP-\\d{6}", message = "Invalid TTP ID format")
    @Indexed(unique = true)
    private String ttpId;

    @NotBlank
    @Size(max = 100)
    private String tactic;

    @NotBlank
    @Size(max = 150)
    private String technique;

    @Size(max = 2000)
    private String description;

    @DBRef
    private List<MongoAttackPattern> relatedAttackPatterns;

    @DBRef
    private List<MongoMalware> associatedMalwares;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

