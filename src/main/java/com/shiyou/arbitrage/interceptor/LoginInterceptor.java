package com.shiyou.arbitrage.interceptor;

import com.shiyou.arbitrage.annotation.Auth;
import com.shiyou.arbitrage.common.ObjectMapper;
import com.shiyou.arbitrage.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.shiyou.arbitrage.data.model.Contant.SESSION_KEY;
import static com.shiyou.arbitrage.data.model.Contant.USER_CODE_SESSION_KEY;


/**
 * @Package: com.shiyou.arbitrage.interceptor
 * @Project: arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/19 0019 11:42
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Value("${access.control.allow.origin:*}")
    private String originName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {
            if (request.getAttribute(SESSION_KEY) == null || request.getParameter(USER_CODE_SESSION_KEY) == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, originName);
                response.getWriter().append(ObjectMapper.toJson(new Result<>(401)));
                return false;
            }else {
                return true;
            }
        }
        return true;
    }
}
