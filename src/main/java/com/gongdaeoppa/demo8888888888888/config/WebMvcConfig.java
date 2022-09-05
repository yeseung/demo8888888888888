package com.gongdaeoppa.demo8888888888888.config;

import com.gongdaeoppa.demo8888888888888.interceptor.BeforeActionInterceptor;
import com.gongdaeoppa.demo8888888888888.interceptor.NeedAdminInterceptor;
import com.gongdaeoppa.demo8888888888888.interceptor.NeedToLoginInterceptor;
import com.gongdaeoppa.demo8888888888888.interceptor.NeedToLogoutInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    // beforeActionInterceptor 인터셉터 불러오기

    //@Qualifier("beforeActionInterceptor")
    //private final HandlerInterceptor beforeActionInterceptor;

    private final BeforeActionInterceptor beforeActionInterceptor;

    private final NeedToLoginInterceptor needToLoginInterceptor;
    private final NeedToLogoutInterceptor needToLogoutInterceptor;


    private final NeedAdminInterceptor needAdminInterceptor;


    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    // 이 함수는 인터셉터를 적용하는 역할을 합니다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // beforeActionInterceptor 인터셉터가 모든 액션 실행전에 실행되도록 처리
        registry.addInterceptor(beforeActionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/resource/**")
                .excludePathPatterns("/error");

        registry.addInterceptor(needToLoginInterceptor)
                .addPathPatterns("/mpaUsr/article/write")
                .addPathPatterns("/mpaUsr/article/doWrite")
                .addPathPatterns("/mpaUsr/article/doDelete")
                .addPathPatterns("/mpaUsr/article/modify")
                .addPathPatterns("/mpaUsr/article/doModify")

                .addPathPatterns("/mpaUsr/reply/doWrite")
                .addPathPatterns("/mpaUsr/reply/doDelete")
                .addPathPatterns("/mpaUsr/reply/modify")
                .addPathPatterns("/mpaUsr/reply/doModify")

                .addPathPatterns("/mpaUsr/reply/doWrite")
                .addPathPatterns("/mpaUsr/reply/doDelete")
                .addPathPatterns("/mpaUsr/reply/doDeleteAjax")

                .addPathPatterns("/mpaUsr/member/modify")
                .addPathPatterns("/mpaUsr/member/doModify")
                .addPathPatterns("/mpaUsr/member/checkPassword")
                .addPathPatterns("/mpaUsr/member/doCheckPassword");

        registry.addInterceptor(needToLogoutInterceptor)
                .addPathPatterns("/mpaUsr/member/findLoginId")
                .addPathPatterns("/mpaUsr/member/doFindLoginId")
                .addPathPatterns("/mpaUsr/member/findLoginPw")
                .addPathPatterns("/mpaUsr/member/doFindLoginPw")

                .addPathPatterns("/mpaUsr/member/login")
                .addPathPatterns("/mpaUsr/member/doLogin")

                .addPathPatterns("/mpaUsr/member/getLoginIdDup")

                .addPathPatterns("/mpaUsr/member/join")
                .addPathPatterns("/mpaUsr/member/doJoin")
                .addPathPatterns("/mpaUsr/member/findLoginId")
                .addPathPatterns("/mpaUsr/member/doFindLoginId")
                .addPathPatterns("/mpaUsr/member/findLoginPw")
                .addPathPatterns("/mpaUsr/member/doFindLoginPw");




        registry.addInterceptor(needAdminInterceptor)
                .addPathPatterns("/mpaAdm/**")
                .excludePathPatterns("/mpaAdm/member/findLoginId")
                .excludePathPatterns("/mpaAdm/member/doFindLoginId")
                .excludePathPatterns("/mpaAdm/member/findLoginPw")
                .excludePathPatterns("/mpaAdm/member/doFindLoginPw")
                .excludePathPatterns("/mpaAdm/member/login")
                .excludePathPatterns("/mpaAdm/member/doLogin")
                .excludePathPatterns("/mpaAdm/member/getLoginIdDup")
                .excludePathPatterns("/mpaAdm/member/join")
                .excludePathPatterns("/mpaAdm/member/doJoin")
                .excludePathPatterns("/mpaAdm/member/findLoginId")
                .excludePathPatterns("/mpaAdm/member/doFindLoginId")
                .excludePathPatterns("/mpaAdm/member/findLoginPw")
                .excludePathPatterns("/mpaAdm/member/doFindLoginPw");

        registry.addInterceptor(needToLogoutInterceptor)
                .addPathPatterns("/mpaAdm/member/findLoginId")
                .addPathPatterns("/mpaAdm/member/doFindLoginId")
                .addPathPatterns("/mpaAdm/member/findLoginPw")
                .addPathPatterns("/mpaAdm/member/doFindLoginPw")
                .addPathPatterns("/mpaAdm/member/login")
                .addPathPatterns("/mpaAdm/member/doLogin")
                .addPathPatterns("/mpaAdm/member/getLoginIdDup")
                .addPathPatterns("/mpaAdm/member/join")
                .addPathPatterns("/mpaAdm/member/doJoin")
                .addPathPatterns("/mpaAdm/member/findLoginId")
                .addPathPatterns("/mpaAdm/member/doFindLoginId")
                .addPathPatterns("/mpaAdm/member/findLoginPw")
                .addPathPatterns("/mpaAdm/member/doFindLoginPw");




    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/gen/**").addResourceLocations("file:///" + genFileDirPath + "/")
                .setCachePeriod(20);
    }


}