package lab11.blogs.repositories;

import lab11.blogs.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepositories extends JpaRepository<Tag, Integer> {
    Tag findById(int id);
    Tag findByName(String name);
}
