package WebInfo_248.ForumWeb.Service.Impl;

import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Repository.DiscussionRepository;
import WebInfo_248.ForumWeb.Service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Override
    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    @Override
    public Discussion getDiscussionById(int id) {
        return discussionRepository.findById(id).orElse(null);
    }

    @Override
    public void addDiscussion(Discussion discussion) {
        discussionRepository.save(discussion);
    }

    @Override
    public void deleteDiscussion(int id) {
        discussionRepository.deleteById(id);
    }

}
