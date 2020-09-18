package be.niedel.edgrpc.grpcclient;

import be.niedel.edgrpc.proto.Address;
import be.niedel.edgrpc.proto.AgreementGrpc;
import be.niedel.edgrpc.proto.AgreementGrpc.AgreementBlockingStub;
import be.niedel.edgrpc.proto.CreateAgreementRequest;
import be.niedel.edgrpc.proto.CreateAgreementResponse;
import be.niedel.edgrpc.proto.CreateEmployeeRequest;
import be.niedel.edgrpc.proto.CreateEmployeeResponse;
import be.niedel.edgrpc.proto.CreateEmployerRequest;
import be.niedel.edgrpc.proto.CreateEmployerResponse;
import be.niedel.edgrpc.proto.EmployeeGrpc;
import be.niedel.edgrpc.proto.EmployeeGrpc.EmployeeBlockingStub;
import be.niedel.edgrpc.proto.EmployerGrpc;
import be.niedel.edgrpc.proto.EmployerGrpc.EmployerBlockingStub;
import be.niedel.edgrpc.proto.Id;
import be.niedel.edgrpc.proto.Name;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;
import java.util.logging.Logger;

public class GrpcClient {

    private static final Logger LOGGER = Logger.getLogger(GrpcClient.class.getName());
    private static final int PORT = 6565;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        EmployerBlockingStub grpcClientForEmployer = EmployerGrpc.newBlockingStub(channel);
        var employer = createEmployer(grpcClientForEmployer);

        EmployeeBlockingStub grpcClientForEmployee = EmployeeGrpc.newBlockingStub(channel);
        var employee = createEmployee(grpcClientForEmployee);

        AgreementBlockingStub grpcClientForAgreement = AgreementGrpc.newBlockingStub(channel);
        createAgreement(grpcClientForAgreement, employer.getId(), employee.getId());
        createAgreement(grpcClientForAgreement, employer.getId(), employee.getId());
        createAgreement(grpcClientForAgreement, employer.getId(), employee.getId());

        channel.shutdown();
    }

    private static CreateEmployerResponse createEmployer(EmployerBlockingStub client) {
        CreateEmployerResponse response = client.createEmployer(CreateEmployerRequest.newBuilder()
                .setId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setName("Genericompany X").build());
        LOGGER.info("Employer created: " + response.getId().getValue());
        return response;
    }

    private static CreateEmployeeResponse createEmployee(EmployeeBlockingStub client) {
        CreateEmployeeResponse response = client.createEmployee(CreateEmployeeRequest.newBuilder()
                .setId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setName(Name.newBuilder()
                        .setFirstName("Tim")
                        .setFirstName("Timmens"))
                .setAddress(Address.newBuilder()
                        .setStreet("Steenstraat")
                        .setStreetNumber("185B")
                        .setMunicipality("Leuven")
                        .setCountry("Belgium").build()).build());
        LOGGER.info("Employee created: " + response.getId().getValue());
        return response;
    }

    private static CreateAgreementResponse createAgreement(AgreementBlockingStub client, Id employerId, Id employeeId) {
        CreateAgreementRequest createAgreementRequest = CreateAgreementRequest.newBuilder()
                .setId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setEmployerId(employerId)
                .setEmployeeId(employeeId)
                .setStatute("WHITE_COLLAR")
                .setWage(5000.85)
                .build();
        CreateAgreementResponse createAgreementResponse = client.createAgreement(createAgreementRequest);
        LOGGER.info("Agreement created: " + createAgreementResponse.getId().getValue());
        return createAgreementResponse;
    }

}
