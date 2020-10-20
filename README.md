# Resources
- https://www.baeldung.com/grpc-introduction
- https://grpc.io/docs/languages/java/quickstart/
- https://github.com/LogNet/grpc-spring-boot-starter
- https://www.baeldung.com/java-microbenchmark-harness
- https://dzone.com/articles/is-protobuf-5x-faster-than-json

# Steps
1. Hoe een client & server op te zetten waarbij server via spring boot is:
    - spring-boot-grpc starter (io.github project): https://github.com/LogNet/grpc-spring-boot-starter
2. Hoe het mogelijk maken dat business toch nog JSON kan insturen en wij dit toch nog kunnen verwerken? Puur voor manuele inspectie / testing
    - https://github.com/protocolbuffers/protobuf/blob/master/java/util/src/test/java/com/google/protobuf/util/JsonFormatTest.java
    - https://developers.google.com/protocol-buffers/docs/proto3#json
    - https://developers.google.com/protocol-buffers/docs/reference/java/com/google/protobuf/util/package-summary

# TODO
1. Versionering van proto file + apart trekken + client / server apart
    - Alle events in 1 jar? Wat dan bij breaking change / aanpassing van event -> nieuwe versie waar iedereen op moet dependen...
1. Performantie setup doen
1. Client - Server / Server - Server is duidelijk (zie ook: https://thenewstack.io/build-real-world-microservices-with-grpc/).
    - Hiervoor is gRPC geschikt
    - Maar, indien we met het outbox pattern willen werken (debezium & pulsar), is protobuf daar dan een voordeel (gRPC komt niet in the picture dan)? 

# Open Questions
- Proto files zitten nu in aparte jar (kan zelfs aparte repo). Versionering zijn dan via semver apart kunnen gebeuren.
    - Nadeel: 1 grote artifact
    
# Agenda
0. Designing Data Intensive Applications (niet te lang op blijven hangen)
1. Protobuf as an alternative for JSON (niet te lang op blijven hangen)
    - Contract Driven / Specification / Schemas
        - Json -> contract testing with PACT, Spring contract,....
    - Pojo, Stub & Client generation (ook zonder gRPC)?
    - Size: uncompressed versus compressed (compared with json)
    - Performance: serialization vs deserialization speed
    - Backwards Compatibility (v2 versus v3 -> optional, required,...)
    - Disadvantage (binary, but encodes to JSON -> kunnen voorbeeld aanhalen)
2. Designing Microservices with gRPC and Protobuf instead of Http-based restful WS & JSON (niet te lang op blijven hangen)
    - Demo, setup,... 
3. Designing Microservices with Pulsar & JSON versus Pulsar & Protobuf
    - Limitations on size?

## Pulsar
- https://pulsar.apache.org/docs/en/2.6.1/concepts-schema-registry/#supported-schema-formats
- https://pulsar.apache.org/docs/en/develop-binary-protocol
