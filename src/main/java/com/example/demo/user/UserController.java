package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="user")
public class UserController {

    private  final  UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void register(@RequestBody User user){
        userService.addNewUser(user);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        userService.updateUser(Id, name, email);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long Id){
        userService.deleteUser(Id);
    }
}
