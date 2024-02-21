package employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class EmployeesTestApplication {

    @Bean
    @ServiceConnection
    @RestartScope
    public PostgreSQLContainer<?> postgresqlDbContainer() {
        return new PostgreSQLContainer<>("postgres")
                .withCreateContainerCmdModifier(mod -> mod.withName("employees-test-postgres"))
                .withReuse(true);
    }


    @Bean
    @RestartScope
    @ServiceConnection(name = "openzipkin/zipkin")
    public GenericContainer<?> zipkinContainer() {
        return new GenericContainer<>("openzipkin/zipkin")
                .withCreateContainerCmdModifier(mod -> mod.withName("employees-test-zipkin"))
                .withExposedPorts(9411)
                .withReuse(true);
    }

    public static void main(String[] args) {
        SpringApplication
                .from(EmployeesApplication::main)
                .run(args);
    }
}
