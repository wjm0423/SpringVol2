package bcsd.BoardAPI.controller;

import bcsd.BoardAPI.domain.User;
import bcsd.BoardAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    public User insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserByUserId(@PathVariable String userId) {
        return userService.getUserByUserId(userId);
    }

    @PutMapping("/{userId}/name")
    public void updateUserName(@PathVariable String userId, @RequestBody User user) {
        userService.updateUserName(userId, user.getUserName());
    }

    @PutMapping("/{userId}/pw")
    public void updateUserPw(@PathVariable String userId, @RequestBody User user) {
        userService.updateUserPw(userId, user.getUserPw());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}
