package lab11.blogs.repositories;

import lab11.blogs.models.Comment;
import lab11.blogs.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepositories extends JpaRepository<Comment, Integer> {
    Comment findById(int id);
    List<Comment> findByPostAndStatus(Post post, String status);
    List<Comment> findByStatus(String status);
}
