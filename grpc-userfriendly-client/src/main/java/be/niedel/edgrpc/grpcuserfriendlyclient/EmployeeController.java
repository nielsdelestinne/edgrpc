package be.niedel.edgrpc.grpcuserfriendlyclient;

import be.niedel.edgrpc.proto.Address;
import be.niedel.edgrpc.proto.CreateEmployeeRequest;
import be.niedel.edgrpc.proto.EmployeeGrpc;
import be.niedel.edgrpc.proto.Id;
import be.niedel.edgrpc.proto.Name;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "employees")
public class EmployeeController {

    private static final int PORT = 6565;
    private static final String HOST = "localhost";

    @GetMapping(path = "/model")
    public String getModel() throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(CreateEmployeeRequest.newBuilder()
                .setId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setName(Name.newBuilder()
                        .setFirstName("Ann")
                        .setFirstName("Annenko"))
                .setAddress(Address.newBuilder()
                        .setStreet("Dirtroad")
                        .setStreetNumber("120B")
                        .setMunicipality("Leuven")
                        .setCountry("Belgium").build()).build());
    }

    @PostMapping
    public String create(@RequestBody String createEmployeeRequestAsJson) throws InvalidProtocolBufferException {
        return EmployeeGrpc.newBlockingStub(ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build())
                .createEmployee(parseToRequestObject(createEmployeeRequestAsJson))
                .getId().getValue();
    }

    private CreateEmployeeRequest parseToRequestObject(String employeeAsJson) throws InvalidProtocolBufferException {
        CreateEmployeeRequest.Builder builder = CreateEmployeeRequest.newBuilder();
        JsonFormat.parser().merge(employeeAsJson, builder);
        return builder.build();
    }

}
