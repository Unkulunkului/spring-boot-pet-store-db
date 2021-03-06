package by.home.springbootpetstoredb.interceptor;


import by.home.springbootpetstoredb.entity.Token;
import by.home.springbootpetstoredb.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("X-Token");
        Token valid = tokenService.valid(header);
        if(valid!=null && valid.getKey().equals(header)){
            return true;
        }
        response.setStatus(403);
        return false;
    }
}
