package com.gongdaeoppa.demo8888888888888.controller;


import com.gongdaeoppa.demo8888888888888.dto.Article;
import com.gongdaeoppa.demo8888888888888.dto.Member;
import com.gongdaeoppa.demo8888888888888.dto.ResultData;
import com.gongdaeoppa.demo8888888888888.dto.Rq;
import com.gongdaeoppa.demo8888888888888.service.GenFileService;
import com.gongdaeoppa.demo8888888888888.service.MemberService;
import com.gongdaeoppa.demo8888888888888.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MpaUsrMemberController {

    private final MemberService memberService;

    private final GenFileService genFileService;

    @RequestMapping("/mpaUsr/member/join")
    public String showJoin(HttpServletRequest req) {
        return "mpaUsr/member/join";
    }

    @RequestMapping("/mpaUsr/member/doJoin")
    //@ResponseBody
    //public Map doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
    //public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
    public String doJoin(HttpServletRequest req,
                         String loginId,
                         String loginPw,
                         String name,
                         String nickname,
                         String cellphoneNo,
                         String email,
                         MultipartRequest multipartRequest) {

//        Map<String, MultipartFile> fileMap1 = multipartRequest.getFileMap();
//        System.out.println("fileMap1 =========== " + fileMap1);
//
//        if (true) return "";



        Member oldMember = memberService.getMemberByLoginId(loginId);

        if (oldMember != null) {
            return Util.msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인아이디 입니다.");
        }





        oldMember = memberService.getMemberByNameAndEmail(name, email);

        if (oldMember != null) {
            return Util.msgAndBack(req, String.format("%s님은 이미 %s 메일주소로 %s 에 가입하셨습니다.", name, email, oldMember.getRegDate()));
        }





        ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

        if (joinRd.isFail()) {
            return Util.msgAndBack(req, joinRd.getMsg());
        }



        int newMemberId = (int)joinRd.getBody().get("id");

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        for (String fileInputName : fileMap.keySet()) {
            MultipartFile multipartFile = fileMap.get(fileInputName);

            if ( multipartFile.isEmpty() == false ) {
                genFileService.save(multipartFile, newMemberId);
            }
        }




        //return Util.mapOf("loginId", loginId, "loginPw", loginPw, "name", name, "nickname", nickname, "cellphoneNo", cellphoneNo, "email", email);
        return Util.msgAndReplace(req, joinRd.getMsg(), "/");

    }


    @RequestMapping("/mpaUsr/member/login")
    public String showLogin(HttpServletRequest req) {
        return "mpaUsr/member/login";
    }

    @RequestMapping("/mpaUsr/member/doLogin")
    public String doLogin(HttpServletRequest req, HttpSession session, String loginId, String loginPw, String redirectUri) {


        if ( Util.isEmpty(redirectUri) ) {
            redirectUri = "/";
        }

        Member member = memberService.getMemberByLoginId(loginId);


        if (member == null) {
            return Util.msgAndBack(req, loginId + "(은)는 존재하지 않는 로그인아이디 입니다.");
        }

        if (member.getLoginPw().equals(loginPw) == false) {
        //if (member.getLoginPw().equals(Util.sha256(loginPw)) == false) {
            return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.____^^______");
        }

        //HttpSession session = req.getSession();
        session.setAttribute("loginedMemberId", member.getId());

        session.setAttribute("loginedMemberJsonStr", member.toJsonStr());

        String msg = "환영합니다_________.";

        boolean needToChangePassword = memberService.needToChangePassword(member.getId());

        if ( needToChangePassword ) {
            //msg = "현재 비밀번호를 사용한지 10일이 지났습니다. 비밀번호를 변경해주세요.";
            msg = "현재 비밀번호를 사용한지 " + memberService.getNeedToChangePasswordFreeDays() + "일이 지났습니다. 비밀번호를 변경해주세요.";
            redirectUri = "/mpaUsr/member/mypage";
        }

        boolean isUsingTempPassword = memberService.usingTempPassword(member.getId());

        //boolean isUsingTempPassword = memberService.isUsingTempPassword(member.getId());


        if ( isUsingTempPassword ) {
            msg = "임시 비밀번호를 변경해주세요.";
            redirectUri = "/mpaUsr/member/mypage";
        }


        //System.out.println("redirectUri = " + redirectUri);
        return Util.msgAndReplace(req, msg, redirectUri);
    }


    @RequestMapping("/mpaUsr/member/doLogout")
    public String doLogout(HttpServletRequest req, HttpSession session) {
        session.removeAttribute("loginedMemberId");

        String msg = "로그아웃 되었습니다.";
        return Util.msgAndReplace(req, msg, "/");
    }


//    @RequestMapping("/mpaUsr/member/1")
//    @ResponseBody
//    public String showOptional() {
//        //memberService.getMemberById(2).isPresent();
//        Optional<Member> member1 = Optional.ofNullable(memberService.getMemberById(2));
//        //String s = member1.get().toString();
//        //System.out.println("s = " + s);
//        log.debug(member1.toString());
//        System.out.println("member1 = " + member1);
//
//        if (member1.isPresent()) {
//            return "-----------------------------1";
//        } else {
//            return "-----------------------------2";
//        }
//    }


    @RequestMapping("/mpaUsr/member/findLoginId")
    public String showFindLoginId(HttpServletRequest req) {
        return "mpaUsr/member/findLoginId";
    }

    @RequestMapping("/mpaUsr/member/doFindLoginId")
    public String doFindLoginId(HttpServletRequest req, String name, String email, String redirectUri) {
        if (Util.isEmpty(redirectUri)) {
            redirectUri = "/";
        }

        Member member = memberService.getMemberByNameAndEmail(name, email);

        if (member == null) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        return Util.msgAndBack(req, String.format("회원님의 아이디는 `%s` 입니다.", member.getLoginId()));
    }









    @RequestMapping("/mpaUsr/member/findLoginPw")
    public String showFindLoginPw(HttpServletRequest req) {
        return "mpaUsr/member/findLoginPw";
    }

    @RequestMapping("/mpaUsr/member/doFindLoginPw")
    public String doFindLoginPw(HttpServletRequest req, String loginId, String name, String email, String redirectUri) {
        if (Util.isEmpty(redirectUri)) {
            redirectUri = "/";
        }

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        if (member.getName().equals(name) == false) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        if (member.getEmail().equals(email) == false) {
            return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
        }

        ResultData notifyTempLoginPwByEmailRs = memberService.notifyTempLoginPwByEmail(member);

        return Util.msgAndReplace(req, notifyTempLoginPwByEmailRs.getMsg(), redirectUri);
    }













    @RequestMapping("/mpaUsr/member/mypage")
    public String showMypage(HttpServletRequest req) {
        return "mpaUsr/member/mypage";
    }








    // checkPasswordAuthCode : 체크비밀번호인증코드
    @RequestMapping("/mpaUsr/member/modify")
    public String showModify(HttpServletRequest req,  String checkPasswordAuthCode) {

        Member loginedMember = ((Rq) req.getAttribute("rq")).getLoginedMember();
        ResultData checkValidCheckPasswordAuthCodeResultData = memberService
                .checkValidCheckPasswordAuthCode(loginedMember.getId(), checkPasswordAuthCode);

        if ( checkValidCheckPasswordAuthCodeResultData.isFail() ) {
            return Util.msgAndBack(req, checkValidCheckPasswordAuthCodeResultData.getMsg());
        }

        return "mpaUsr/member/modify";
    }

    @RequestMapping("/mpaUsr/member/doModify")
    public String doModify(HttpServletRequest req, String loginPw, String name, String
            nickname, String cellphoneNo, String email, String checkPasswordAuthCode,
                           MultipartRequest multipartRequest) {

        Member loginedMember = ((Rq) req.getAttribute("rq")).getLoginedMember();
        ResultData checkValidCheckPasswordAuthCodeResultData = memberService
                .checkValidCheckPasswordAuthCode(loginedMember.getId(), checkPasswordAuthCode);

        if ( checkValidCheckPasswordAuthCodeResultData.isFail() ) {
            return Util.msgAndBack(req, checkValidCheckPasswordAuthCodeResultData.getMsg());
        }

        if (loginPw != null && loginPw.trim().length() == 0) {
            loginPw = null;
        }

        int id = ((Rq) req.getAttribute("rq")).getLoginedMemberId();
        ResultData modifyRd = memberService.modify(id, loginPw, name, nickname, cellphoneNo, email);

        if (modifyRd.isFail()) {
            return Util.msgAndBack(req, modifyRd.getMsg());
        }



        if ( req.getParameter("deleteFile__member__0__extra__profileImg__1") != null ) {
            genFileService.deleteGenFile("member", loginedMember.getId(), "extra", "profileImg", 1);
        }



        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        for (String fileInputName : fileMap.keySet()) {
            MultipartFile multipartFile = fileMap.get(fileInputName);

            if ( multipartFile.isEmpty() == false ) {
                genFileService.save(multipartFile, loginedMember.getId());
            }
        }



        return Util.msgAndReplace(req, modifyRd.getMsg(), "/");
    }










    @RequestMapping("/mpaUsr/member/checkPassword")
    public String showCheckPassword(HttpServletRequest req) {
        return "mpaUsr/member/checkPassword";
    }

    @RequestMapping("/mpaUsr/member/doCheckPassword")
    public String doCheckPassword(HttpServletRequest req, String loginPw, String redirectUri) {
        Member loginedMember = ((Rq) req.getAttribute("rq")).getLoginedMember();

        if (loginedMember.getLoginPw().equals(loginPw) == false) {
            return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.");
        }

        String authCode = memberService.genCheckPasswordAuthCode(loginedMember.getId());

        redirectUri = Util.getNewUri(redirectUri, "checkPasswordAuthCode", authCode);

        return Util.msgAndReplace(req, "", redirectUri);
    }








    @RequestMapping("/mpaUsr/member/getLoginIdDup")
    @ResponseBody
    public ResultData getLoginIdDup(HttpServletRequest req, String loginId) {
        Member member = memberService.getMemberByLoginId(loginId);

        if (member != null) {
            return new ResultData("F-1", "해당 로그인아이디는 이미 사용중입니다.", "loginId", loginId);
        }

        return new ResultData("S-1", "사용가능한 로그인 아이디 입니다.", "loginId", loginId);
    }





}