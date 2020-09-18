package be.niedel.edgrpc.grpcapi;

import be.niedel.edgrpc.domain.employee.Address;
import be.niedel.edgrpc.domain.employee.Employee;
import be.niedel.edgrpc.domain.employee.EmployeeRepository;
import be.niedel.edgrpc.domain.employee.Name;
import be.niedel.edgrpc.proto.CreateEmployeeRequest;
import be.niedel.edgrpc.proto.CreateEmployeeResponse;
import be.niedel.edgrpc.proto.CreateEmployerResponse;
import be.niedel.edgrpc.proto.EmployeeGrpc;
import be.niedel.edgrpc.proto.Id;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Named;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Named
@Transactional
@GRpcService
public class EmployeeService extends EmployeeGrpc.EmployeeImplBase {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(CreateEmployeeRequest request, StreamObserver<CreateEmployeeResponse> responseObserver) {
        var employee = employeeRepository.save(
                new Employee(
                        UUID.fromString(request.getId().getValue()),
                        new Name(
                                request.getName().getFirstName(),
                                request.getName().getLastName()),
                        new Address(
                                request.getAddress().getStreet(),
                                request.getAddress().getStreetNumber(),
                                request.getAddress().getMunicipality(),
                                request.getAddress().getCountry())));

        responseObserver.onNext(CreateEmployeeResponse.newBuilder()
                .setId(Id.newBuilder()
                        .setValue(employee.getId().toString()).build()).build());
        responseObserver.onCompleted();
    }
}
