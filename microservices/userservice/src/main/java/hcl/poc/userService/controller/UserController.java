package hcl.poc.userService.controller;

import hcl.poc.api.user.UserAPI;
import hcl.poc.api.user.UserDTO;
import hcl.poc.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
