package lab11.blogs.repositories;

import lab11.blogs.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostRepositories extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    Post findById(int id);
    Post findByTitle(String title);
    Page<Post> findAllByStatus(String status, Pageable pageable);
}
