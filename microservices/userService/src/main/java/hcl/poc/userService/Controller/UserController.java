package hcl.poc.userService.Controller;

import hcl.poc.api.User.UserAPI;
import hcl.poc.userService.Model.User;
import hcl.poc.userService.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController implements UserAPI {

    @Autowired
    private UserService service;

    @Operation(summary = "This method gets all the existing users")
    public Flux<hcl.poc.api.User.User> getAllUsers(){
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
    public Mono<hcl.poc.api.User.User> getOneUser(@RequestParam("id") Long id){
        return service.getRepository().findById(id);
    }

    @Operation(summary = "Add a new user")
    public void addOneUser(@RequestBody User newUser){
        service.getRepository().save(newUser);
    }

    @Operation(summary = "Delete a user by id")
    public void deleteOneUser(@RequestParam("id") Long id){
        service.getRepository().deleteById(id);
    }

    @Operation(summary = "Update a user by id or add if the id doesn't already exist")
    public void putUser(@RequestBody hcl.poc.api.User.User user, @PathVariable("id") Long id){
        service.getRepository().findById(id).map(u -> new ResponseEntity("Updated", HttpStatus.OK))
                                            .orElseGet(() -> service.getRepository().save(user));
    }
}
