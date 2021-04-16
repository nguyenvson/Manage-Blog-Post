package lab11.blogs.services;

import lab11.blogs.models.Comment;
import lab11.blogs.models.Post;
import lab11.blogs.models.User;
import lab11.blogs.repositories.CommentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepositories commentRepositories;

    @Autowired
    private PostService postService;

    @Transactional
    public void save(Comment comment){
        commentRepositories.save(comment);
    }

    @Transactional
    public Comment findById(int id){
        return commentRepositories.findById(id);
    }

    /**
     * This function to find the newest comment.
     * @return
     */
    @Transactional
    public Comment findLast(){
        List<Comment> comments = findAll();
        if(comments.size() == 0){
            return new Comment();
        }
        return  comments.get((comments.size()-1));
    }

    /**
     * This function will find all Comment
     * @return
     */
    @Transactional
    public List<Comment> findAll(){
        return commentRepositories.findAll();
    }

    /**
     * This function will find Disapprove comment.
     * @return
     */
    @Transactional
    public List<Comment> findDisapproveCmt(){
        return commentRepositories.findByStatus("DisApprove");
    }

    /**
     * This function will find Approve comment by Post.
     * @param id
     * @return
     */
    @Transactional
    public List<Comment> findApproveCmtByPostId(int id){
        return commentRepositories.findByPostAndStatus(postService.findById(id), "Approve");
    }

    /**
     * This function will delete Comment.
     * @param comment
     */
    @Transactional
    public void deleteComment(Comment comment){
        commentRepositories.delete(comment);
    }

    /**
     * This function will delete Comment by id
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteCmtById(int id){
        try {
            commentRepositories.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This function will add new comment with user, post and content of comment.
     * @param user
     * @param idPost
     * @param cmt
     */
    @Transactional
    public void addNewComment(User user, int idPost, String cmt){
        Post postToAddNewCmt = postService.findById(idPost);
        Comment comment = new Comment(cmt, user, postToAddNewCmt, "Disapprove");
        commentRepositories.save(comment);
        postService.save(postToAddNewCmt);
    }

    /**
     * This function will update status of comment.
     * @param idCmt
     * @param status
     * @return
     */
    @Transactional
    public boolean updateCmtStatus(int idCmt, String status){
        try {
            Comment commentToUpdate = findById(idCmt);
            if (!commentToUpdate.getStatus().equals(status)){
                commentToUpdate.setStatus(status);
                commentRepositories.save(commentToUpdate);
            }
            return true;
        } catch (NullPointerException e){
            return false;
        }
    }
}
