package orsk.compli.entities.mongo;

import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "threat_actors")
public class MongoThreatActor {

    @Id
    private ObjectId id;

    @NotBlank
    @Size(max = 150)
    @Indexed
    private String name;

    @DBRef(lazy = true)
    private MongoCountry originCountry;

    private MongoGeolocation geolocation;

    private LocalDate firstObserved;

    @DBRef(lazy = true)
    private List<MongoThreatGroup> associatedGroups;

    @Size(max = 50)
    private String operationalStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

