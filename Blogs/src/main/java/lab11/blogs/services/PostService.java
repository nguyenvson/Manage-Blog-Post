package lab11.blogs.services;

import lab11.blogs.models.Comment;
import lab11.blogs.models.Post;
import lab11.blogs.models.Tag;
import lab11.blogs.models.User;
import lab11.blogs.repositories.PostRepositories;
import lab11.blogs.repositories.TagRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepositories postRepositories;

    @Autowired
    private TagRepositories tagRepositories;

    @Autowired
    private CommentService commentService;

    @Transactional
    public void save(Post post){
        postRepositories.save(post);
    }

    @Transactional
    public Post findById(int id){
        return postRepositories.findById(id);
    }

    @Transactional
    public List<Post> findAll(){
        return postRepositories.findAll();
    }

    /**
     * This will find list Post base on page and size of page
     * @param page
     * @param size
     * @return
     */
    @Transactional
    public List<Post> findAllPublicByPaging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateCreate").descending());
        return postRepositories.findAllByStatus("published", pageable).getContent();
    }

    /**
     * This function will create of up date post with Post, tags, and User parameters
     * This function will check does title of Post already exist or not,
     * if yes it will return false to noted user that title already exist,
     * if not it will separate the String tags to List<Tag> then add in Post and add User for Post,
     * if id of Post != 0, this function will update Post,
     * if id == 0, this will create new Post.
     * @param post
     * @param tags
     * @param user
     * @return
     */
    @Transactional
    public boolean createOrUpdatePost(Post post, String tags, User user){
        if (post.getId() == 0){
            try {
                if (postRepositories.findByTitle(post.getTitle()).getTitle() != null){
                    return false;
                }
            } catch (NullPointerException e) {
            }
        }
        tags = tags.replace(", ",",");
        tags = tags.replace(" ,",",");
        List<String> list = Arrays.asList(tags.split(","));
        List<Tag> tagList = new ArrayList<>();
        for (String s: list) {
            Tag tag = tagRepositories.findByName(s);
            if (tag != null){
                tag.setFrequency(tag.getFrequency() + 1); ;
                tagList.add(tag);
                tagRepositories.save(tag);
            }
            else {
                Tag newTag = new Tag(s, 1);
                tagList.add(newTag);
                tagRepositories.save(newTag);
            }
        }
        if (post.getId() != 0){
            Post postToUpdate = findById(post.getId());
            postToUpdate.setTitle(post.getTitle());
            postToUpdate.setContent(post.getContent());
            postToUpdate.setStatus(post.getStatus());
            postToUpdate.setTags(tagList);
            postRepositories.save(postToUpdate);
        }
        else {
            post.setUser(user);
            post.setTags(tagList);
            postRepositories.save(post);
        }
        return true;
    }

    /**
     * This function will delete Post base on Id.
     * @param id
     */
    @Transactional
    public void deletePostById(int id){
        Post postToDelete = postRepositories.findById(id);
        if (postToDelete != null){
            for (Comment comment: postToDelete.getComments()) {
                try {
                    commentService.deleteComment(comment);
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
            try {
                postRepositories.delete(postToDelete);
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * This function will find Post by title.
     * @param title
     * @return
     */
    @Transactional
    public Post findByTitle(String title){
        return postRepositories.findByTitle(title);
    }

    @Transactional
    public List<Post> findPostByTag(int idTag){
        return tagRepositories.findById(idTag).getPosts();
    }
}
