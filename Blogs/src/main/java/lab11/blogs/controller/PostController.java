package lab11.blogs.controller;

import lab11.blogs.models.Post;
import lab11.blogs.models.User;
import lab11.blogs.services.CommentService;
import lab11.blogs.services.PostService;
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
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    /**
     * This Controller is called when user just logged in or user click "Manage Post" link in view ManagePostPage.
     * This controller will check does user roll "admin" or not, if yes this controller will lead user to ManagePostPage.
     * if not this controller will lead user to Home page.
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @GetMapping("/managePost")
    public String managePost(Model model, @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                model.addAttribute("allPosts", postService.findAll());
                model.addAttribute("allTags",tagService.findAll());
                model.addAttribute("countCmtDisapprove", commentService.findDisapproveCmt().size());
                model.addAttribute("lastComment",commentService.findLast());
                return "ManagePostPage";
            }
        } catch (NullPointerException e){
            return "redirect:/blog/home";
        }
        return "redirect:/blog/home";
    }

    /**
     * This Controller is called when user click "edit" icon in view ManagePostPage beside the post.
     * This controller will check does user roll "admin" or not, if yes this controller will lead user to CreatePostPage with
     * parameter of the post to edit.
     * @param id
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @GetMapping("/managePost/edit/{id}")
    public String editPost(@PathVariable int id, Model model, @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                Post post = postService.findById(id);
                if (post.getId() != 0){
                    model.addAttribute("postInfor", post);
                    model.addAttribute("tags", post.getTags());
                    model.addAttribute("idPost", post.getId());
                    model.addAttribute("oldTitlePost", post.getTitle());
                    return "CreatePostPage";
                }
                return "redirect:/blog/managePost";
            }
        } catch (NullPointerException e){
            return "redirect:/blog/home";
        }
        return "redirect:/blog/home";
    }

    /**
     * This Controller is called when user click "delete" icon in view ManagePostPage beside the post.
     * This controller will check does user roll "admin" or not, if yes this controller call service to delete Post.
     * @param id
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @GetMapping("/managePost/delete/{id}")
    public ResponseEntity deletePost(@PathVariable int id, Model model, @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                postService.deletePostById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (NullPointerException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * This Controller is called when user click "Create new post" link in view ManagePostPage beside the post.
     * This controller will check does user roll "admin" or not, if yes this controller will lead user to CreatePostPage
     * to create new post.
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @GetMapping("/createPost")
    public String createPost(Model model, @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                model.addAttribute("postInfor", new Post());
                model.addAttribute("tags", null);
                model.addAttribute("allTags",tagService.findAll());
                model.addAttribute("countCmtDisapprove", commentService.findDisapproveCmt().size());
                model.addAttribute("lastComment",commentService.findLast());
                return "CreatePostPage";
            }
        } catch (NullPointerException e){
            return "redirect:/blog/home";
        }
        return "redirect:/blog/home";
    }

    /**
     * This Controller is called when user click "Submit" button in view CreatePostPage.
     * This controller will check does user roll "admin" or not, if yes this controller will call service to create new Post.
     * @param post
     * @param tagsInput
     * @param idPost
     * @param model
     * @param userAlreadyLogin
     * @return
     */
    @PostMapping("/managePost/createOrUpdatePost")
    public String createOrEditPost(@ModelAttribute("postInfor") Post post,
                                   @RequestParam("tagsInput") String tagsInput,
                                   @RequestParam("idPost") String idPost, Model model,
                                   @SessionAttribute("userAlreadyLogin") User userAlreadyLogin){
        try {
            if (userAlreadyLogin.getRoll().equals("admin")){
                if (idPost != ""){
                    post.setId(Integer.valueOf(idPost));
                }
                if (!postService.createOrUpdatePost(post, tagsInput, userAlreadyLogin)){
                    model.addAttribute("postInfor", post);
                    model.addAttribute("tags", post.getTags());
                    model.addAttribute("idPost", post.getId());
                    model.addAttribute("TitleExist", true);
                    return "CreatePostPage";
                }
            }
        } catch (NullPointerException e){
            return "redirect:/blog/home";
        }
        return "redirect:/blog/managePost";
    }

    /**
     * This Controller is called by ajax when user click link "Load more" in Home view.
     * This controller will return list of Post to show more in Home view.
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/loadMorePost")
    public ResponseEntity<List<Post>> getAllUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<Post> posts = postService.findAllPublicByPaging(page, size);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * This controller is called when user click in name of tag in view, this will return the Post regarding to tag name.
     * @param idTag
     * @param model
     * @return
     */
    @GetMapping("/findPostByTag/{idTag}")
    public String findPostByTag(@PathVariable int idTag, Model model){
        model.addAttribute("lastComment",commentService.findLast());
        model.addAttribute("allTags",tagService.findAll());
        model.addAttribute("allPublicPosts", postService.findPostByTag(idTag));
        return "HomePage";
    }
}
