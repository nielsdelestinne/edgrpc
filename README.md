# Resources
- https://www.baeldung.com/grpc-introduction
- https://grpc.io/docs/languages/java/quickstart/
- https://github.com/LogNet/grpc-spring-boot-starter

# Steps
1. Hoe een client & server op te zetten waarbij server via spring boot is:
    - spring-boot-grpc starter (io.github project): https://github.com/LogNet/grpc-spring-boot-starter
2. Hoe het mogelijk maken dat business toch nog JSON kan insturen en wij dit toch nog kunnen verwerken? Puur voor manuele inspectie / testing
    - https://github.com/protocolbuffers/protobuf/blob/master/java/util/src/test/java/com/google/protobuf/util/JsonFormatTest.java
    - https://developers.google.com/protocol-buffers/docs/proto3#json
    - https://developers.google.com/protocol-buffers/docs/reference/java/com/google/protobuf/util/package-summary

# TODO
1. Opkuis
1. Versionering van proto file + apart trekken + client / server apart
    - Alle events in 1 jar? Wat dan bij breaking change / aanpassing van event -> nieuwe versie waar iedereen op moet dependen...
1. Performantie setup doen