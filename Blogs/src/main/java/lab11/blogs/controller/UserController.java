package lab11.blogs.controller;

import lab11.blogs.models.User;
import lab11.blogs.services.CommentService;
import lab11.blogs.services.PostService;
import lab11.blogs.services.TagService;
import lab11.blogs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/blog")
@SessionAttributes("userAlreadyLogin")
public class UserController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @ModelAttribute("userAlreadyLogin")
    private User userAlreadyLogin(){
        return new User();
    }

    /**
     * This Controller is called when user click "Insert Data" Link in navbar from view.
     * This will insert data for You.
     * @return
     */
    @GetMapping("/insertData")
    public String insertData(){
        userService.insertData();
        return "redirect:/blog/home";
    }

    /**
     * This Controller is called when user click Home in view, this will return the Home view with Post.
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("lastComment",commentService.findLast());
        model.addAttribute("allTags",tagService.findAll());
        model.addAttribute("allPublicPosts", postService.findAllPublicByPaging(0,2));
        return "HomePage";
    }

    /**
     * This Controller is called when user click About in view, this will return the About view.
     * @param model
     * @return
     */
    @GetMapping("/about")
    public String about(Model model){
        return "AboutPage";
    }

    /**
     * This Controller is called when user click Contact in view, this will return the Contact view.
     * @param model
     * @return
     */
    @GetMapping("/contact")
    public String contact(Model model){
        return "ContactPage";
    }

    /**
     * This Controller is called when user click Login in view, this will return the Login view
     * (with username and password automatic filled in)
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userToLogin",new User("username1", "123456789"));
        return "LoginPage";
    }

    /**
     * This Controller is called when user click Login button in Login page, this will call service to check username and password
     * then return LoginPage if wrong username or password, or redirect to managePost if user roll "admin",
     * redirect to home if user not roll "admin".
     * @param userLogin
     * @param model
     * @return
     */
    @PostMapping("/loginuser")
    public String userLogin(@ModelAttribute("userToLogin") User userLogin, Model model){
        User user = userService.findByUsernameAndPassword(userLogin.getUserName(),userLogin.getPassword());
        try {
            model.addAttribute("userAlreadyLogin", user);
            if (user.getRoll().equals("admin")){
                return "redirect:/blog/managePost";
            }
            else {
                return "redirect:/blog/home";
            }
        } catch (NullPointerException e){
            model.addAttribute("userToLogin",new User("username1", "123456789"));
            model.addAttribute("LoginWrongError", true);
            return "LoginPage";
        }
    }

    /**
     * This Controller is called when user click Logout in view, this will reset all value in session.
     * @param model
     * @param httpsession
     * @param status
     * @return
     */
    @GetMapping("/logout")
    public String logout(Model model, HttpSession httpsession, SessionStatus status){
        /*Mark the current handler's session processing as complete, allowing for cleanup of
        session attributes.*/
        status.setComplete();

        /* Invalidates this session then unbinds any objects boundto it. */
        httpsession.invalidate();
        return "redirect:/blog/home";
    }
}
