package com.gongdaeoppa.demo8888888888888.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attr {
    private int id;
    private String regDate;
    private String updateDate;
    private String expireDate;
    private String relTypeCode;
    private int relId;
    private String typeCode;
    private String type2Code;
    private String value;
}