package lab11.blogs.services;

import lab11.blogs.models.Tag;
import lab11.blogs.repositories.TagRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepositories tagRepositories;

    /**
     * This function will save or update Tag.
     * @param tag
     */
    @Transactional
    public void save(Tag tag){
        tagRepositories.save(tag);
    }

    /**
     * This function will find Tag by id.
     * @param id
     * @return
     */
    @Transactional
    public Tag findById(int id){
        return tagRepositories.findById(id);
    }

    /**
     * This function will find all Tag.
     * @return
     */
    @Transactional
    public List<Tag> findAll(){
        return tagRepositories.findAll();
    }
}
