package lab11.blogs.controller;

import lab11.blogs.models.Comment;
import lab11.blogs.models.User;
import lab11.blogs.services.CommentService;
import lab11.blogs.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class CommentController {

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    /**
     * This Controller is called by ajax when user click link "Comment(...)" in Post form Home view.
     * This controller will return a list comment base on param post id.
     * @param id
     * @return
     */
    @GetMapping("/post/loadComment")
    public ResponseEntity<List<Comment>> getCommentOfPost(@RequestParam("idPost") int id){
        List<Comment> comments = commentService.findApproveCmtByPostId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * This Controller is called when user when user add new cmt in Post form Home view.
     * This controller will check does user already logined or not yet then call commentService to add new comment.
     * @param idPost
     * @param cmt
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @GetMapping("/new-cmt/{idPost}/{cmt}")
    public ResponseEntity newCmt(@PathVariable("idPost") int idPost, @PathVariable("cmt") String cmt, Model model,
                                 @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        if (userAlreadyLogin.getUserName() != null){
            commentService.addNewComment(userAlreadyLogin, idPost, cmt);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

    /**
     * This Controller is called when user click "Approve Comment(...) in view ManagePostPage.
     * This controller will check does user roll "admin" or not, if yes this controller will lead user
     * to ApproveCommentPage view.
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @GetMapping("/approveCmt")
    public String approveCmt(Model model, @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                model.addAttribute("allTags",tagService.findAll());
                model.addAttribute("allCmts", commentService.findAll());
                model.addAttribute("countCmtDisapprove", commentService.findDisapproveCmt().size());
                model.addAttribute("lastComment",commentService.findLast());
                return "ApproveCommentPage";
            }
        } catch (NullPointerException e){
            return "redirect:/blog/home";
        }
        return "redirect:/blog/home";
    }

    /**
     * This Controller is called by ajax when user change status of comment in ApproveCommentPage view..
     * This controller will check does user roll "admin" or not, if yes this will call service to change the status of comment.
     * @param idCmt
     * @param status
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @PutMapping("/updateStatusCmt/{idCmt}/{status}")
    public ResponseEntity updateStatusCmt(@PathVariable("idCmt") int idCmt, @PathVariable("status") String status,
                                          Model model, @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                if (commentService.updateCmtStatus(idCmt, status)){
                    return new ResponseEntity(HttpStatus.OK);
                }
                return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            }
        } catch (NullPointerException e){
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

    /**
     * This Controller is called by ajax when user click delete icon in ApproveCommentPage view.
     * This controller will check does user roll "admin" or not, if yes this will call service delete comment.
     * @param idCmt
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @DeleteMapping("/deleteCmt/{idCmt}")
    public ResponseEntity updateStatusCmt(@PathVariable("idCmt") int idCmt, Model model,
                                          @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                if (commentService.deleteCmtById(idCmt)){
                    return new ResponseEntity(HttpStatus.OK);
                }
                return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            }
        } catch (NullPointerException e){
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }
}
