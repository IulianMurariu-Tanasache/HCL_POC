package hcl.poc.userService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer user_id;
    private String email;
    private String name;
    private String password;
    private boolean isAdmin;
    private String phone;
    private String address;
}
