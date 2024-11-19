package orsk.compli.entities.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collection = "refresh_tokens")
public class MongoRefreshToken {

    @Id
    private String id;

    private String token;

    private Instant expiryDate;

    @DBRef
    private MongoUser user;
}