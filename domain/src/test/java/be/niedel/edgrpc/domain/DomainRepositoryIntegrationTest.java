package be.niedel.edgrpc.domain;

import be.niedel.edgrpc.domain.agreement.Agreement;
import be.niedel.edgrpc.domain.agreement.AgreementRepository;
import be.niedel.edgrpc.domain.agreement.Statute;
import be.niedel.edgrpc.domain.employee.Address;
import be.niedel.edgrpc.domain.employee.Employee;
import be.niedel.edgrpc.domain.employee.EmployeeRepository;
import be.niedel.edgrpc.domain.employee.Name;
import be.niedel.edgrpc.domain.employer.Employer;
import be.niedel.edgrpc.domain.employer.EmployerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DomainSpringTestApplication.class)
public class DomainRepositoryIntegrationTest {

    private final EmployeeRepository employeeRepository;
    private final EmployerRepository employerRepository;
    private final AgreementRepository agreementRepository;

    public DomainRepositoryIntegrationTest(@Autowired EmployeeRepository employeeRepository,
                                           @Autowired EmployerRepository employerRepository,
                                           @Autowired AgreementRepository agreementRepository) {
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
        this.agreementRepository = agreementRepository;
    }

    @Test
    public void givenEmployeeAndEmployer_whenSaveAgreement_thenSuccess() {
        Employee employee = new Employee(
                UUID.randomUUID(),
                new Name("Jim", "Jimmens"),
                new Address("Jimstreet", "16A", "Leuven", "Belgium"));
        Employer employer = new Employer(UUID.randomUUID(), "Generic Company Inc.");
        employeeRepository.save(employee);
        employerRepository.save(employer);

        Agreement agreementToPersist = new Agreement(UUID.randomUUID(), employer.getId(), employee.getId(), Statute.WHITE_COLLAR, new BigDecimal("2500.00"));
        Agreement persistedAgreement = agreementRepository.save(agreementToPersist);

        assertThat(persistedAgreement).usingRecursiveComparison().isEqualTo(agreementToPersist);
    }

}