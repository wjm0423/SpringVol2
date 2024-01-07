package bcsd.BoardAPI.service;

import bcsd.BoardAPI.security.JWTProvider;
import bcsd.BoardAPI.domain.JWT;
import bcsd.BoardAPI.domain.User;
import bcsd.BoardAPI.domain.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JWTProvider jwtProvider;

    @Transactional
    @Override
    public JWT signIn(String userName, String userPw) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, userPw);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JWT jwt = jwtProvider.generateToken(authentication);

        return jwt;
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateUserName(String userId, String userName) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(userName);
            userRepository.save(existingUser);
        }
    }

    @Override
    public void updateUserPw(String userId, String userPw) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUserPw(userPw);
            userRepository.save(existingUser);
        }
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
