package com.shiyou.arbitrage.filter;

import com.shiyou.arbitrage.config.ParameterRequestWrapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.shiyou.arbitrage.data.model.Contant.SESSION_KEY;

/**
 * @Package: com.shiyou.arbitrage.filter
 * @Project: arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/19 0019 10:57
 */

@WebFilter(filterName = "SessionFilter",urlPatterns = "/*")
public class SessionFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ParameterRequestWrapper httpRequest = new ParameterRequestWrapper((HttpServletRequest) servletRequest);


    }

    @Override
    public void destroy() {

    }

    private void handle(ParameterRequestWrapper httpRequest){
        String sessionId = httpRequest.getHeader(SESSION_KEY);
        if (StringUtils.isBlank(sessionId)){
            Object session = httpRequest.getSession().getAttribute(SESSION_KEY);
            if (session != null){
                sessionId = session.toString();
            }
        }
        if (StringUtils.isNotBlank(sessionId)){

        }


    }
}
