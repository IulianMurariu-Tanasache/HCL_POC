package hcl.poc.userService.Service;

import hcl.poc.userService.Model.User;
import hcl.poc.userService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserRepository getRepository() {
        return repository;
    }

    public void modifyUser(User newUser){
        repository.deleteById(newUser.getUser_id());
        repository.save(newUser);
    }
}
