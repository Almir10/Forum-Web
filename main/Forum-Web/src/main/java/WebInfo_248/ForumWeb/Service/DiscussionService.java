package WebInfo_248.ForumWeb.Service;

import WebInfo_248.ForumWeb.Models.Discussion;

import java.util.List;

public interface DiscussionService {
    List<Discussion> getAllDiscussions();


    Discussion getDiscussionById(int id);
    void addDiscussion(Discussion discussion);

    void deleteDiscussion(int id);
}
