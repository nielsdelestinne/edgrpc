package be.niedel.edgrpc.domain.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Employee {

    @Id
    private UUID id;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    public Employee(UUID id, Name name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
