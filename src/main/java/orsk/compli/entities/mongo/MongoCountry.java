package orsk.compli.entities.mongo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Document(collection = "countries")
public class MongoCountry {

    @Id
    private ObjectId id;

    @NotBlank
    @Size(min = 2, max = 3)
    @Indexed(unique = true)
    private String code;

    @NotBlank
    @Size(max = 100)
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

