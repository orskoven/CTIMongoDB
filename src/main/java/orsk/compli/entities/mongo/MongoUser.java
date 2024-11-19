package orsk.compli.entities.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Getter
@Setter
@Document(collection = "users") // Specify MongoDB collection name
public class MongoUser {
    @Id
    private String id; // Aligns with MongoDB's ObjectId

    @Indexed(unique = true) // Enhances query performance
    private String username;

    private String password;

    @Indexed(unique = true)
    private String email;

    private Boolean enabled = false;

    private Boolean consentToDataUsage = false;

    @DBRef // Reference to Role documents
    private Set<MongoRole> roles;
}
