package hcl.poc.api.User;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserAPI {

    @GetMapping("/user")
    Flux<User> getAllUsers();

    @GetMapping("/user")
    Mono<User> getOneUser(@RequestParam("id") Long id);

//    @Operation(summary = "Add a new user")
    @PostMapping("/user")
    void addOneUser(@RequestBody User newUser);

    //@Operation(summary = "Delete a user by id")
    @DeleteMapping("/user") // should I return http response on error?
    void deleteOneUser(@RequestBody User user, @RequestParam("id") Long id);

    //@Operation(summary = "Update a user by id or add if the id doesn't already exist")
    @PutMapping("/user")
    void putUser(@RequestBody User user, @PathVariable("id") Long id);
}
