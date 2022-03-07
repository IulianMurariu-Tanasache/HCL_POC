package hcl.poc.productservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"hcl.poc.productService.model"} )
@EnableJpaRepositories(basePackages = {"hcl.poc.productService.repository"})
public class ConfigClass {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
