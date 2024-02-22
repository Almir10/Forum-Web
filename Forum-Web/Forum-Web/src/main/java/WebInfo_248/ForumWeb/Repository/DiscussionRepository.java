package WebInfo_248.ForumWeb.Repository;

import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {

    List<Discussion> findAll();
    List<Discussion> findByCreatedBy(User user);
}
