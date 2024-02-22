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
    public List<Discussion> getDiscussionsForUser(int userId) {
        // Implementirajte logiku dobavljanja diskusija za korisnika ovdje
        // Primjer:
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return discussionRepository.findByCreatedBy(user);
        }
        return Collections.emptyList();
    }

    @Override
    public void addCommentForUser(int userId, CommentDto commentDTO) {
        // Implementirajte logiku dodavanja komentara za korisnika ovdje
        // Primjer:
        User user = userRepository.findById(userId).orElse(null);
        Discussion discussion = discussionRepository.findById(commentDTO.getDiscussionId()).orElse(null);

        if (user != null && discussion != null) {
            Comments newComment = new Comments();
            newComment.setContent(commentDTO.getContent());
            newComment.setPostedDate(LocalDateTime.now());
            newComment.setPostedBy(user);
            newComment.setDiscussion(discussion);

            commentRepository.save(newComment);
        }
    }
}