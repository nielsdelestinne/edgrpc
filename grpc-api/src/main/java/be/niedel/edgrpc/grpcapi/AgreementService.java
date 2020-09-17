package be.niedel.edgrpc.grpcapi;

import be.niedel.edgrpc.domain.agreement.Agreement;
import be.niedel.edgrpc.domain.agreement.AgreementRepository;
import be.niedel.edgrpc.domain.agreement.Statute;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Named;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Named
@Transactional
@GRpcService
public class AgreementService extends AgreementGrpc.AgreementImplBase {

    private final AgreementRepository agreementRepository;

    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @Override
    public void createAgreement(CreateAgreementRequest request, StreamObserver<CreateAgreementResponse> responseObserver) {
        var agreement = agreementRepository.save(
                new Agreement(
                        UUID.fromString(request.getId().getValue()),
                        UUID.fromString(request.getEmployerId().getValue()),
                        UUID.fromString(request.getEmployeeId().getValue()),
                        Statute.valueOf(request.getStatute()),
                        BigDecimal.valueOf(request.getWage())
                )
        );

        responseObserver.onNext(
                CreateAgreementResponse.newBuilder()
                        .setId(Id.newBuilder()
                                .setValue(agreement.getId().toString()).build()).build());
        responseObserver.onCompleted();
    }
}
