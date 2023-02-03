package com.example.demo.user;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if (u.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException(
                    "Student by id:"+ id+ " doesn't exist");
        }
        userRepository.deleteById(id);
    }
    @Transactional
    public void updateUser(Long id, String name, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Student by id:"+ id+ " doesn't exist"
        ));

        if (name != null && name.length() > 0 && !user.getName().equals(name)){
            user.setName(name);
        }

        if (email != null && email.length() > 0 && !user.getEmail().equals(email)){
            Optional<User> u = userRepository.findByEmail(email);
            if (u.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }


}
