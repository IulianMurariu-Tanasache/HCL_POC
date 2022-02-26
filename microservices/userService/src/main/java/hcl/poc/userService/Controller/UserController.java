package hcl.poc.userService.Controller;

import hcl.poc.api.User.UserAPI;
import hcl.poc.api.User.UserDTO;
import hcl.poc.userService.Model.User;
import hcl.poc.userService.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController implements UserAPI {

    @Autowired
    private UserService service;

    @Override
    public Flux<UserDTO> getAllUsers() {
        return Flux.fromIterable(service.getAllUser());
    }

    @Override
    public Mono<UserDTO> getOneUser(Long id) {
        return Mono.just(service.getOneUser(id));
    }

    @Override
    public void addOneUser(UserDTO newUserDTO) {
        service.addOneUser(newUserDTO);
    }

    @Override
    public void deleteOneUser(Long id) {
        service.deleteOneUser(id);
    }

    @Override
    public void putUser(UserDTO userDTO, Long id) {
        service.modifyUser(id, userDTO);
    }
}
