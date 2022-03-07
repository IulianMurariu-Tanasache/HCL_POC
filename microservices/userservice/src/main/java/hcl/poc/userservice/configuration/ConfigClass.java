package hcl.poc.userservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"hcl.poc.userService.model"} )
@EnableJpaRepositories(basePackages = {"hcl.poc.userService.repository"})
public class ConfigClass {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
