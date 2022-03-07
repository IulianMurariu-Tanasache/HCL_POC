package hcl.poc.userservice.controller;

import hcl.poc.api.user.UserAPI;
import hcl.poc.api.user.UserDTO;
import hcl.poc.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
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
    public Mono<UserDTO> addOneUser(UserDTO newUserDTO) {
        return Mono.just(service.addOneUser(newUserDTO));
    }

    @Override
    public void deleteOneUser(Long id) {
        service.deleteOneUser(id);
    }

    @Override
    public Mono<UserDTO> putUser(UserDTO userDTO, Long id) {
        return Mono.just(service.modifyUser(id, userDTO));
    }
}
