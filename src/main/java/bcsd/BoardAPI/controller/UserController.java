package bcsd.BoardAPI.controller;

import bcsd.BoardAPI.domain.JWT;
import bcsd.BoardAPI.domain.SignIn;
import bcsd.BoardAPI.domain.User;
import bcsd.BoardAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-in")
    public JWT signIn(@RequestBody SignIn signIn) {
        String userName = signIn.getUserName();
        String userPw = signIn.getUserPw();
        JWT jwt = userService.signIn(userName, userPw);
        log.info("request username = {}, password = {}", userName, userPw);
        log.info("JWT accessToken = {}, refreshToken = {}", jwt.getAccessToken(), jwt.getRefreshToken());
        return jwt;
    }

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
        userService.updateUserName(userId, user.getUsername());
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
