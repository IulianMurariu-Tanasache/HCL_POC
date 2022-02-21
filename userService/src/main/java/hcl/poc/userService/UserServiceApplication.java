package hcl.poc.userService;

import hcl.poc.userService.Model.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Supplier;

@OpenAPIDefinition
@SpringBootApplication
@EntityScan(basePackages = {"hcl.poc.userService.Model"} )
@EnableJpaRepositories(basePackages = {"hcl.poc.userService.Repository"})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	Consumer<Mono<String>> userConsumer(){
		return string -> {
			System.out.println("Consumed string " + string);
		};
	}

//	@Bean
//	public Supplier<Flux<User>> userSupplier(){
//		return () -> Flux.fromIterable(service.getRepository().findAll());
//	}

}
