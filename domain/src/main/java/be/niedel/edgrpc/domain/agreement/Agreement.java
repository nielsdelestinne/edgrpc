package be.niedel.edgrpc.domain.agreement;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Agreement {

    @Id
    private UUID id;
    private UUID employerId;
    private UUID employeeId;

    @Enumerated(value = EnumType.STRING)
    private Statute statute;
    private BigDecimal wage;

    public Agreement(UUID id, UUID employerId, UUID employeeId, Statute statute, BigDecimal wage) {
        this.id = id;
        this.employerId = employerId;
        this.employeeId = employeeId;
        this.statute = statute;
        this.wage = wage;
    }

}
