package lab11.blogs.repositories;

import lab11.blogs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUserNameAndPassword(String userName, String password);
}
