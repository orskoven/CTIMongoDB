package orsk.compli.entities.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document(collection = "roles")
public class MongoRole {
    @Id
    private String id;

    @Indexed(unique = true) // Enhances query performance
    private String name; // Role name, e.g., "ROLE_USER"

    private Set<Privilege> privileges; // Embedded privileges directly in the role

    @Getter
    @Setter
    public static class Privilege {
        private String name; // Privilege name, e.g., "READ_PRIVILEGE"
    }
}