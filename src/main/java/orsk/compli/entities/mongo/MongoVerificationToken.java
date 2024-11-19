package orsk.compli.entities.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collection = "verification_tokens")
public class MongoVerificationToken {

    @Id
    private String id;

    private String token;

    @DBRef
    private MongoUser user;

    private Instant expiryDate;
}