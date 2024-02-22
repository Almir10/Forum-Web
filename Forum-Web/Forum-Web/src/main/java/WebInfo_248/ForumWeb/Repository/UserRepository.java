package WebInfo_248.ForumWeb.Repository;

import WebInfo_248.ForumWeb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


    User findByEmail(String email);
    User findByEmailAndPasswordAndActiveTrue(String email, String password);
}