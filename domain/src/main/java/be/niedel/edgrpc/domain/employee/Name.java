package be.niedel.edgrpc.domain.employee;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Name {

    private String firstName;
    private String lastName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
