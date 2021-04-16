package lab11.blogs.interceptor;

import lab11.blogs.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {
    /**
     * This postHandle will check does user roll "admin" or not then show the ManagePost in navbar.
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null){
            Map<String, Object> user = modelAndView.getModel();
            User user1 = (User) user.get("userAlreadyLogin");
            try {
                if (user1.getRoll().equals("admin")){
                    modelAndView.addObject("admin", true);
                }
                modelAndView.addObject("alreadyLogin", true);
            } catch (NullPointerException e){
                modelAndView.addObject("alreadyLogin", false);
            }
        }
    }
}
