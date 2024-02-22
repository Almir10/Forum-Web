package WebInfo_248.ForumWeb.Service.Impl;

import WebInfo_248.ForumWeb.Models.Comments;
import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Models.User;
import WebInfo_248.ForumWeb.Repository.CommentsRepository;
import WebInfo_248.ForumWeb.Repository.DiscussionRepository;
import WebInfo_248.ForumWeb.Repository.UserRepository;
import WebInfo_248.ForumWeb.Service.UserService;
import WebInfo_248.ForumWeb.dto.CommentDto;
import WebInfo_248.ForumWeb.dto.LoginDto;
import WebInfo_248.ForumWeb.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private CommentsRepository commentRepository;

    @Override
    public void registerUser(UserDto userDTO) {

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setEmail(userDTO.getEmail());
        newUser.setRole("user"); // Postavljanje početne uloge na "user"
        newUser.setActive(true); // Novi korisnik je aktivan
        userRepository.save(newUser);
    }

    @Override
    public User loginUser(LoginDto loginDTO) {

        return userRepository.findByEmailAndPasswordAndActiveTrue(
                loginDTO.getEmail(), loginDTO.getPassword());
    }


    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public void deactivateUser(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent(user -> {
            user.setActive(false);
            userRepository.save(user);
        });
    }

    @Override
    public void activateUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setActive(true);
            userRepository.save(user);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}