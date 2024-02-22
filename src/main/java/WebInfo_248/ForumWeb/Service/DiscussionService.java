package WebInfo_248.ForumWeb.Service;

import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.dto.CommentDto;
import WebInfo_248.ForumWeb.dto.DiscussionDto;

import java.util.List;

public interface DiscussionService {
    List<Discussion> getAllDiscussions();

    void addDiscussion(Discussion discussion);

    void deleteDiscussion(int id);

    Discussion getDiscussionById(int id);

    void addCommentToDiscussion(int discussionId, CommentDto commentDTO);

}
