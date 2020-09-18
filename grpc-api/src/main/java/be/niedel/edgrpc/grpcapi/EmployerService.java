package be.niedel.edgrpc.grpcapi;

import be.niedel.edgrpc.domain.employer.Employer;
import be.niedel.edgrpc.domain.employer.EmployerRepository;
import be.niedel.edgrpc.proto.CreateEmployerRequest;
import be.niedel.edgrpc.proto.CreateEmployerResponse;
import be.niedel.edgrpc.proto.EmployerGrpc;
import be.niedel.edgrpc.proto.Id;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Named;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Named
@Transactional
@GRpcService
public class EmployerService extends EmployerGrpc.EmployerImplBase {

    private final EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public void createEmployer(CreateEmployerRequest request, StreamObserver<CreateEmployerResponse> responseObserver) {
        var employer = employerRepository.save(
                new Employer(
                        UUID.fromString(request.getId().getValue()),
                        request.getName()));

        responseObserver.onNext(CreateEmployerResponse.newBuilder()
                .setId(Id.newBuilder()
                        .setValue(employer.getId().toString()).build()).build());
        responseObserver.onCompleted();
    }
}
