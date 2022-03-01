package hcl.poc.api.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserAPI {

    @GetMapping("/user")
    Flux<UserDTO> getAllUsers();

    @GetMapping("/user/{id}")
    Mono<UserDTO> getOneUser(@PathVariable("id") Long id);

    @Operation(summary = "Add a new user")
    @PostMapping("/user")
    void addOneUser(@RequestBody UserDTO newUserDTO);

    @Operation(summary = "Delete a user by id")
    @DeleteMapping("/user/{id}")
    void deleteOneUser(@PathVariable("id") Long id);

    @Operation(summary = "Update a user by id or add if the id doesn't already exist")
    @PutMapping("/user/{id}")
    void putUser(@RequestBody UserDTO userDTO, @PathVariable("id") Long id);
}
