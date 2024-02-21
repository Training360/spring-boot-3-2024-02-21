package training360.employeessb3clientdemo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

//import java.util.List;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class EmployeesSb3ClientDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesSb3ClientDemoApplication.class, args);
	}

//	private WebClient.Builder webClientBuilder;

	private RestClient.Builder restClientBuilder;

	@Override
	public void run(String... args) {
		log.info("Application run");

//		var webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
//		var response = webClient.get().uri("/api/employees").retrieve().bodyToMono(String.class).block();
//		var response = webClient.get().uri("/api/employees").retrieve().bodyToFlux(EmployeeResource.class).collectList().block();
//		log.info("Response: {}", response);

		var restClient = restClientBuilder
				.baseUrl("http://localhost:8080")
				.build();

//		var response = restClient.get().uri("/api/employees")
//				.retrieve().body(new ParameterizedTypeReference<List<EmployeeResource>>() {});
//

		var factory = HttpServiceProxyFactory
				.builderFor(RestClientAdapter.create(restClient)).build();
		var client = factory.createClient(EmployeeService.class);

		log.info("Response: {}", client.listEmployees());
	}
}
