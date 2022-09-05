package com.gongdaeoppa.demo8888888888888.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gongdaeoppa.demo8888888888888.service.MemberService;
import com.gongdaeoppa.demo8888888888888.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    private int id;
    private String regDate;
    private String updateDate;
    private String loginId;
    private String loginPw;
    private int authLevel;
    private String name;
    private String nickname;
    private String cellphoneNo;
    private String email;
    private boolean delStatus;
    private String delDate;

    public String toJsonStr() {
        return Util.toJsonStr(this);
    }

    public String getProfileImgUri() {
        return "/common/genFile/file/member/" + id + "/extra/profileImg/1";
    }

    public String getProfileFallbackImgUri() {
        return "https://via.placeholder.com/300?text=^_^";
    }

    public String getProfileFallbackImgOnErrorHtmlAttr() {
        return "this.src = '" + getProfileFallbackImgUri() + "'";
    }

    public String getRemoveProfileImgIfNotExistsOnErrorHtmlAttr() {
        return "$(this).remove();";
    }

    public String getAuthLevelName() {
        return MemberService.getAuthLevelName(this);
    }

    public String getAuthLevelNameColor() {
        return MemberService.getAuthLevelNameColor(this);
    }
}