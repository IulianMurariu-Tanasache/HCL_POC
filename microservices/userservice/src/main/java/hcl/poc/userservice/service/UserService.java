package hcl.poc.userservice.service;

import hcl.com.util.NotFoundException;
import hcl.poc.api.user.UserDTO;
import hcl.poc.userservice.model.User;
import hcl.poc.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO modifyUser(Long id, UserDTO newUser){
        User user = repository.getById(id);

        user.setId(id);
        user.setAddress(newUser.getAddress());
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        user.setAdmin(newUser.isAdmin());
        user.setEmail(newUser.getEmail());

        repository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getOneUser(Long id){
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new),UserDTO.class);
    }

    public List<UserDTO> getAllUser(){
        List<UserDTO> listDTO = new ArrayList<>();
        List<User> listUser = repository.findAll();

        for(User user : listUser){
            listDTO.add(modelMapper.map(user, UserDTO.class));
        }

        return listDTO;
    }

    public void deleteOneUser(Long id){
        repository.deleteById(id);
    }

    public UserDTO addOneUser(UserDTO userDTO){
        repository.save(modelMapper.map(userDTO,User.class));
        return userDTO;
    }
}
