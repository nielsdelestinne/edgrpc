package be.niedel.edgrpc.benchmark;

import be.niedel.edgrpc.proto.Address;
import be.niedel.edgrpc.proto.CreateEmployeeRequest;
import be.niedel.edgrpc.proto.Id;
import be.niedel.edgrpc.proto.Name;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.util.Arrays;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SizeComparison {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        CreateEmployeeRequest createEmployeeRequest = CreateEmployeeRequest.newBuilder()
                .setId(Id.newBuilder().setValue(UUID.randomUUID().toString()))
                .setName(Name.newBuilder()
                        .setFirstName("Tim")
                        .setLastName("Timmens"))
                .setAddress(Address.newBuilder()
                        .setStreet("Steenstraat")
                        .setStreetNumber("185B")
                        .setMunicipality("Leuven")
                        .setCountry("Belgium").build()).build();

        byte[] bytesToString = createEmployeeRequest.toString().getBytes(UTF_8);
        System.out.println("(A) Proto-generated object (toString) encoded to bytes using UTF-8");
        System.out.println(createEmployeeRequest.toString());
        System.out.println("Size: " + bytesToString.length + " -> " + Arrays.toString(bytesToString));

        byte[] bytesEncodedByProtobuf = createEmployeeRequest.toByteArray();
        System.out.println("(B) Proto-generated object encoded to bytes using Protobuf encoding");
        System.out.println("Size: " + bytesEncodedByProtobuf.length + " -> " + Arrays.toString(bytesEncodedByProtobuf));

        byte[] bytesJson = JsonFormat.printer().print(createEmployeeRequest).getBytes(UTF_8);
        System.out.println("(C) JSON (text) encoded to bytes using UTF-8 encoding");
        System.out.println(JsonFormat.printer().print(createEmployeeRequest));
        System.out.println("Size: " + bytesJson.length + " -> " + Arrays.toString(bytesJson));

        System.out.println("Difference A & B: %" + String.format("%.2f", ((float) bytesJson.length / bytesToString.length * 100)));
        System.out.println("Difference C & B: %" + String.format("%.2f", ((float) bytesJson.length / bytesEncodedByProtobuf.length * 100)));

    }

}
