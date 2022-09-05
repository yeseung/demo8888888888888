package com.gongdaeoppa.demo8888888888888.dao;

import com.gongdaeoppa.demo8888888888888.dto.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyDao {
    void write(@Param("relTypeCode") String relTypeCode,
               @Param("relId") int relId,
               @Param("memberId") int memberId,
               @Param("body") String body);

    int getLastInsertId();



    List<Reply> getForPrintRepliesByRelTypeCodeAndRelId(
            @Param("relTypeCode") String relTypeCode,
            @Param("relId") int relId);


    Reply getReplyById(@Param("id") int id);

    void delete(@Param("id") int id);






    void modify(@Param("id") int id,
                @Param("body") String body);



}