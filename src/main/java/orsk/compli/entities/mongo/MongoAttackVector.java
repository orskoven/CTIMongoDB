package orsk.compli.entities.mongo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document("attack_vectors")
public class MongoAttackVector {

    @NotBlank
    @Size(max = 150)
    @Indexed
    private String name;

    @Min(1)
    @Max(10)
    private int severityLevel;

    @Size(max = 500)
    private String description;

}

