package lab11.blogs.validator;

import lab11.blogs.models.Post;
import lab11.blogs.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueTitleCheck, String> {

    @Autowired
    private PostService postService;

    public void initialize(UniqueTitleCheck constraintAnnotation) {
    }

    public boolean isValid(String title, ConstraintValidatorContext context) {
        try {
            Post postCheck = postService.findByTitle(title);
            if (postCheck.getTitle() != null){
                return false;
            }
            return true;
        }catch (NullPointerException e){
            return true;
        }
    }
}