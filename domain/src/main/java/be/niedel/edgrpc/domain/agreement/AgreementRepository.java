package be.niedel.edgrpc.domain.agreement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
}
