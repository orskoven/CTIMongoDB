package orsk.compli.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private boolean enabled;

    // Getters and Setters
}