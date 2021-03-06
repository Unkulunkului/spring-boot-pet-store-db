package by.home.springbootpetstoredb.configuration;

import by.home.springbootpetstoredb.interceptor.AdminInterceptor;
import by.home.springbootpetstoredb.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/store/order");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/tag")
                .addPathPatterns("/pet")
                .addPathPatterns("/pet/{id}")
                .addPathPatterns("/pet/findAllByStatus")
                .addPathPatterns("/store/order/inventory");

    }
}
