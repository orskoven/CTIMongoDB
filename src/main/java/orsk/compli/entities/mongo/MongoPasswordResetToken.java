package orsk.compli.entities.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collection = "password_reset_tokens")
public class MongoPasswordResetToken {

    @Id
    private String id; // Align with MongoDB's ObjectId

    private String token;

    @DBRef
    private MongoUser user; // Reference to MongoUser

    private Instant expiryDate;
}