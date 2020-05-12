package sci.travel_app.WalkTheBear;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/searchresults").setViewName("searchresults");
        registry.addViewController("/tripmanager").setViewName("tripmanager");
        registry.addViewController("/placemanager").setViewName("placemanager");
        registry.addViewController("/placedetail").setViewName("placedetail");
        registry.addViewController("/placedetail/").setViewName("placedetail");
        registry.addViewController("/addplace").setViewName("addplace");
        registry.addViewController("/addplaceadmin").setViewName("addplaceadmin");
        registry.addViewController("/planner").setViewName("planner");
        registry.addViewController("/placedetail").setViewName("placedetail");
        registry.addViewController("/site").setViewName("site");
        registry.addViewController("/editprofile").setViewName("editprofile");
        registry.addViewController("/editplaceadmin").setViewName("editplaceadmin");
        registry.addViewController("/deleteplaceadmin").setViewName("deleteplaceadmin");
        registry.addViewController("/edituseradmin").setViewName("edituseradmin");
        registry.addViewController("/deleteuseradmin").setViewName("deleteuseradmin");
        registry.addViewController("/profileinfo").setViewName("profileinfo");
        registry.addViewController("/profilefavorites").setViewName("profilefavorites");
        registry.addViewController("/profileratings").setViewName("profileratings");
        registry.addViewController("/adminfragments").setViewName("adminfragments");
        registry.addViewController("/adminplace").setViewName("place");
        registry.addViewController("/adminuser").setViewName("user");
    }

//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/");
//    }
}
