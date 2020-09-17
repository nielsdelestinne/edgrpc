package be.niedel.edgrpc.grpcapi;

import be.niedel.edgrpc.grpcapi.AgreementGrpc.AgreementBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        AgreementBlockingStub client = AgreementGrpc.newBlockingStub(channel);

        var agreementResponse = client.createAgreement(CreateAgreementRequest.newBuilder()
                .setId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setEmployeeId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setEmployerId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setStatute("WHITE_COLLAR")
                .setWage(5000.85)
                .build()
        );

        System.err.println("Hello: " + agreementResponse.getId().getValue());

        channel.shutdown();
    }

}
