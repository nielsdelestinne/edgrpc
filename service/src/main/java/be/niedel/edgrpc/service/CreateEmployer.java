package be.niedel.edgrpc.service;

import be.niedel.edgrpc.domain.employer.Employer;
import be.niedel.edgrpc.domain.employer.EmployerRepository;
import jakarta.inject.Named;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Named
@Transactional
public class CreateEmployer {

    private final EmployerRepository repository;

    public CreateEmployer(EmployerRepository repository) {
        this.repository = repository;
    }

    public UUID createEmployer(String name) {
        return repository
                .save(new Employer(UUID.randomUUID(), name))
                .getId();
    }

}
