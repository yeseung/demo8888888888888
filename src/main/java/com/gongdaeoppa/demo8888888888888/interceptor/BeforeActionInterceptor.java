package com.gongdaeoppa.demo8888888888888.interceptor;

import com.gongdaeoppa.demo8888888888888.dto.Member;
import com.gongdaeoppa.demo8888888888888.dto.Rq;
import com.gongdaeoppa.demo8888888888888.service.MemberService;
import com.gongdaeoppa.demo8888888888888.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component //("beforeActionInterceptor") // 컴포넌트 이름 설정
@Slf4j
@RequiredArgsConstructor
public class BeforeActionInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        log.debug("#################!!!!");


        //resp.setCharacterEncoding("UTF-8");
        //resp.setContentType("text/html; charset=UTF-8");

        //resp.setContentType("text/html; charset=utf-8");
        //resp.setCharacterEncoding("utf-8");
        //req.setCharacterEncoding("utf-8");

        Map<String, String> paramMap = Util.getParamMap(req);

        HttpSession session = req.getSession();

        Member loginedMember = null;
        int loginedMemberId = 0;

        if (session.getAttribute("loginedMemberId") != null) {
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
        }

        if (loginedMemberId != 0) {
            //loginedMember = memberService.getMemberById(loginedMemberId);


            String loginedMemberJsonStr = (String) session.getAttribute("loginedMemberJsonStr");

            loginedMember = Util.fromJsonStr(loginedMemberJsonStr, Member.class);

        }

        //req.setAttribute("rq", new Rq(loginedMember));


        String currentUri = req.getRequestURI();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            currentUri += "?" + queryString;
        }

        //req.setAttribute("rq", new Rq(loginedMember, currentUrl));
        //req.setAttribute("rq", new Rq(loginedMember, currentUrl, paramMap));





        boolean needToChangePassword = false;

        if ( loginedMember != null ) {
            //needToChangePassword = memberService.needToChangePassword(loginedMember.getId());

            if (session.getAttribute("needToChangePassword") == null) {
                needToChangePassword = memberService.needToChangePassword(loginedMember.getId());

                session.setAttribute("needToChangePassword", needToChangePassword);
            }

            needToChangePassword = (boolean) session.getAttribute("needToChangePassword");
        }

        req.setAttribute("rq", new Rq(isAjax(req), memberService.isAdmin(loginedMember), loginedMember, currentUri, paramMap, needToChangePassword));


        return HandlerInterceptor.super.preHandle(req, resp, handler);
    }









    private boolean isAjax(HttpServletRequest req) {
        String[] pathBits = req.getRequestURI().split("/");

        String controllerTypeCode = "";
        String controllerSubject = "";
        String controllerActName = "";

        if (pathBits.length > 1) {
            controllerTypeCode = pathBits[1];
        }

        if (pathBits.length > 2) {
            controllerSubject = pathBits[2];
        }

        if (pathBits.length > 3) {
            controllerActName = pathBits[3];
        }

        boolean isAjax = false;

        String isAjaxParameter = req.getParameter("isAjax");

        if ( isAjax == false ) {
            if ( controllerActName.startsWith("get") ) {
                isAjax = true;
            }
        }

        if ( isAjax == false ) {
            if (controllerActName.endsWith("Ajax")) {
                isAjax = true;
            }
        }

        if ( isAjax == false ) {
            if (isAjaxParameter != null && isAjaxParameter.equals("Y")) {
                isAjax = true;
            }
        }

        return isAjax;
    }


}