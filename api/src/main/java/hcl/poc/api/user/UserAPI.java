package hcl.poc.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserAPI {

    @Operation(summary = "Get a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value = "/user", produces = "application/json")
    Flux<UserDTO> getAllUsers();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "404", description = "User with specified id not found")
    })
    @Operation(summary = "Get one user by id")
    @GetMapping(value = "/user/{id}", produces = "application/json")
    Mono<UserDTO> getOneUser(@PathVariable("id") Long id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Operation(summary = "Add a new user")
    @PostMapping(value = "/user", consumes = "application/json")
    void addOneUser(@RequestBody UserDTO newUserDTO);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Operation(summary = "Delete a user by id")
    @DeleteMapping("/user/{id}")
    void deleteOneUser(@PathVariable("id") Long id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Operation(summary = "Update a user by id or add if the id doesn't already exist")
    @PutMapping(value = "/user/{id}", consumes = "application/json")
    void putUser(@RequestBody UserDTO userDTO, @PathVariable("id") Long id);
}
