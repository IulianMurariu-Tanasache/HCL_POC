package hcl.poc.userService.Controller;

import hcl.poc.userService.Model.User;
import hcl.poc.userService.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @Operation(summary = "This method gets all the existing users")
    @GetMapping("/all")
    public Flux<User> getAll(){
        return Flux.fromIterable(service.getRepository().findAll());
    }

    @Operation(summary = "This method gets a user by id")
    @ApiResponse(
            responseCode = "200",
            description = "The user was found",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) })
    @ApiResponse(
            responseCode = "404",
            description = "The user was not found")
    @GetMapping("/{id}")
    public ResponseEntity<Mono<User>> getById(@PathVariable("id") Long id){
        return service.getRepository().findById(id)
                    .map(u -> new ResponseEntity<Mono<User>>(Mono.just(u), HttpStatus.OK))
                    .orElse(new ResponseEntity<Mono<User>>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Add a new user")
    @PostMapping
    public void addUser(@RequestBody User newUser){
        service.getRepository().save(newUser);
        //userSupplier();
    }

    @Operation(summary = "Delete a user by id")
    @DeleteMapping("/{id}") // should I return http response on error?
    public void deleteUser(@RequestBody User user, @PathVariable("id") Long id){
        service.getRepository().delete(user);
    }

    @Operation(summary = "Update a user by id or add if the id doesn't already exist")
    @PutMapping("/{id}")
    public ResponseEntity putUser(@RequestBody User user, @PathVariable("id") Long id){
        return service.getRepository().findById(id).map(u -> new ResponseEntity("Updated", HttpStatus.OK)).orElseGet(() -> {
            service.getRepository().save(user);
            return new ResponseEntity("Added", HttpStatus.OK);
        });
    }
}
