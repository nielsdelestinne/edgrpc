package be.niedel.edgrpc.domain.employer;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Employer {

    @Id
    private UUID id;
    private String name;

    public Employer(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
