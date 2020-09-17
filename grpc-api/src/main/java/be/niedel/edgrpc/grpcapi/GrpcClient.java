package be.niedel.edgrpc.grpcapi;

import be.niedel.edgrpc.grpcapi.AgreementGrpc.AgreementBlockingStub;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;

public class GrpcClient {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        AgreementBlockingStub client = AgreementGrpc.newBlockingStub(channel);

        CreateAgreementRequest createAgreementRequest = CreateAgreementRequest.newBuilder()
                .setId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setEmployeeId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setEmployerId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setStatute("WHITE_COLLAR")
                .setWage(5000.85)
                .build();
        var agreementResponse = client.createAgreement(createAgreementRequest);

        System.err.println("Hello: " + agreementResponse.getId().getValue());

        System.out.println(JsonFormat.printer().print(createAgreementRequest));

        CreateAgreementRequest.Builder builder = CreateAgreementRequest.newBuilder();
        JsonFormat.parser().merge("{\n" +
                "  \"id\": {\n" +
                "    \"value\": \"ffad946c-98a4-4a6e-88bd-a69a89575979\"\n" +
                "  },\n" +
                "  \"employerId\": {\n" +
                "    \"value\": \"dd37ab06-3f7b-4ed3-b694-8468ef2468e3\"\n" +
                "  },\n" +
                "  \"employeeId\": {\n" +
                "    \"value\": \"ac22d59c-776a-478f-a6a2-fe3ae19bad26\"\n" +
                "  },\n" +
                "  \"statute\": \"WHITE_COLLAR\",\n" +
                "  \"wage\": 5000.85\n" +
                "}", builder);


        var agreementResponse2 = client.createAgreement(builder.build());

        System.err.println("Hello: " + agreementResponse2.getId().getValue());

        channel.shutdown();
    }

}
