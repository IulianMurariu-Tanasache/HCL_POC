package hcl.poc.productcompositeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
public class ProductCompositeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);
	}

}
