package com.gongdaeoppa.demo8888888888888.interceptor;

import com.gongdaeoppa.demo8888888888888.dto.Rq;
import com.gongdaeoppa.demo8888888888888.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class NeedToLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Rq rq = (Rq) req.getAttribute("rq");

        if (rq.isNotLogined()) {
            //resp.setContentType("text/html; charset=UTF-8");

            //resp.getWriter().append(Util.msgAndBack("로그인 후 이용해주세요."));
            //resp.getWriter().append(Util.msgAndReplace("로그인 후 이용해주세요.", "../member/login"));

//            String afterLoginUrl = "../article/write?boardId=1";
//            String urlEncodedAfterLoginUrl = Util.getUrlEncoded(afterLoginUrl);
//            resp.getWriter().append(Util.msgAndReplace("로그인 후 이용해주세요.", "../member/login?afterLoginUrl=" + urlEncodedAfterLoginUrl));

            //String afterLoginUri = rq.getEncodedCurrentUri();
            //resp.getWriter().append(Util.msgAndReplace("로그인 후 이용해주세요.", "../member/login?afterLoginUri=" + afterLoginUri));




            String resultCode = "F-A";
            String resultMsg = "로그인 후 이용해주세요.";

            if ( rq.isAjax() ) {
                resp.setContentType("application/json; charset=UTF-8");
                resp.getWriter().append("{\"resultCode\":\"" + resultCode + "\",\"msg\":\"" + resultMsg + "\"}");
            }
            else {
                resp.setContentType("text/html; charset=UTF-8");
                String afterLoginUri = rq.getEncodedCurrentUri();
                resp.getWriter().append(Util.msgAndReplace(resultMsg, "../member/login?afterLoginUri=" + afterLoginUri));
            }



            return false;
        }

        return HandlerInterceptor.super.preHandle(req, resp, handler);
    }
}