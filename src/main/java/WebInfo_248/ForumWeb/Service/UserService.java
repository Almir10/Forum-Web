package WebInfo_248.ForumWeb.Service;

import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Models.User;
import WebInfo_248.ForumWeb.dto.CommentDto;
import WebInfo_248.ForumWeb.dto.LoginDto;
import WebInfo_248.ForumWeb.dto.UserDto;

import java.util.List;

public interface UserService {

    void registerUser(UserDto userDTO);
    User loginUser(LoginDto loginDTO);

    User getUserById(int userId);

    List<User> getAllUsers();

    void deactivateUser(int userId);

    void activateUser(int userId);

    User getUserByUsername(String currentUserUsername);
}
