package com.ypgly.yipin.servlet.interceptor;


import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yzy
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token=request.getHeader("authorization");
        /*if(token==null){
            response.sendError(HttpStatus.SC_UNAUTHORIZED, "请登录您的账号！");
            return false;
        }
*/
        if (request.getSession(false) == null) {
            //response.sendError(HttpStatus.SC_UNAUTHORIZED, "登录失效，请重新登录！");
            response.sendError(HttpStatus.SC_UNAUTHORIZED, "请登录您的账号！");
            returnJson(response,"{\"code\":401,\"msg\":\"请登录您的账号！\"}");
            return false;
        } else if (request.getSession().getAttribute("apikey") != null) {
            return true;
        }else {
            response.sendError(HttpStatus.SC_UNAUTHORIZED, "非法apikey，请登录!");
            returnJson(response,"{\"code\":401,\"msg\":\"非法apikey，请登录!\"}");
            return false;
        }

    }
    /*返回客户端数据*/
    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.append(json);
        } catch (IOException e) {
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
