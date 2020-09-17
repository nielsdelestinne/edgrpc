package be.niedel.edgrpc.domain.employee;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String street;
    private String streetNumber;
    private String municipality;
    private String country;

    public Address() {
    }

    public Address(String street, String streetNumber, String municipality, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.municipality = municipality;
        this.country = country;
    }
}
