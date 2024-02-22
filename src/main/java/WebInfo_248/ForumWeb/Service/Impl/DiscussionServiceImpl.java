package WebInfo_248.ForumWeb.Service.Impl;

import WebInfo_248.ForumWeb.Models.Comments;
import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Models.User;
import WebInfo_248.ForumWeb.Repository.DiscussionRepository;
import WebInfo_248.ForumWeb.Service.DiscussionService;
import WebInfo_248.ForumWeb.Service.UserService;
import WebInfo_248.ForumWeb.dto.CommentDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    @Override
    public void addDiscussion(Discussion discussion) {
        discussionRepository.save(discussion);
    }

    @Override
    public void deleteDiscussion(int id) {
        discussionRepository.deleteById(id);
    }


    @Override
    public Discussion getDiscussionById(int id) {
        return discussionRepository.findById(id).orElse(null);
    }
    @Override
    public void addCommentToDiscussion(int discussionId, CommentDto commentDTO) {
        Discussion discussion = getDiscussionById(discussionId);
        if (discussion != null) {
            String currentUserUsername = getCurrentUserUsernameFromSession();
            User currentUser = userService.getUserByUsername(currentUserUsername);

            if (currentUser != null) {
                Comments newComment = new Comments();
                newComment.setContent(commentDTO.getContent());
                newComment.setPostedDate(LocalDateTime.now());
                newComment.setPostedBy(currentUser);
                newComment.setDiscussion(discussion);
                discussion.getComments().add(newComment);
                discussionRepository.save(discussion);
            } else {
                // Handle the case when the current user is not found
            }
        }
    }

    private String getCurrentUserUsernameFromSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        return (String) session.getAttribute("currentUser");
    }


}
