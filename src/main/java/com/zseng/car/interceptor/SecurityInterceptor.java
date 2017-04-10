package com.zseng.car.interceptor;

import com.zseng.car.common.Config;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(SecurityInterceptor.class);

    private boolean canGuestAccess(String path) {

        List<Pattern> patternList = Arrays.asList(Config.PATH_GUEST_CAN_ACCESS_PATTERN);
        return patternList.stream().anyMatch(p -> p.matcher(path).matches());
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        URI uri = new URI(httpServletRequest.getRequestURI());
        String path = uri.getPath().substring(1);
        logger.trace("->");
        logger.trace("path: {}", path);
        logger.trace("query: {}", httpServletRequest.getQueryString());
        logger.trace("parameter: ");
        httpServletRequest.getParameterMap().entrySet().forEach(stringEntry -> logger.trace("{}: {}", stringEntry.getKey(), stringEntry.getValue()));
        logger.trace("<-");

//        if (!canGuestAccess(path))
//        {
//
//        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
