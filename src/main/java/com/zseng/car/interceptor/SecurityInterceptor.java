package com.zseng.car.interceptor;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

//主要是拿来看日志用的
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        URI uri = new URI(httpServletRequest.getRequestURI());
        String path = uri.getPath().substring(1);
        logger.info("->");
        logger.info("path: {}", path);
        logger.info("query: {}", httpServletRequest.getQueryString());
        logger.info("parameter: ");
        if (!(httpServletRequest instanceof MultipartHttpServletRequest)) {
            httpServletRequest.getParameterMap().forEach((key, value) -> logger.info("    {}: {}", key, value));
        }
        logger.info("<-");

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
