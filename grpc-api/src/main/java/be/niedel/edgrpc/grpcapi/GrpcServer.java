package be.niedel.edgrpc.grpcapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "be.niedel.edgrpc")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "be.niedel.edgrpc.domain")
@EntityScan(basePackages = "be.niedel.edgrpc.domain")
public class GrpcServer {

    public static void main(String[] args) {
        SpringApplication.run(GrpcServer.class, args);
    }

}
