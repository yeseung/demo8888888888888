package com.gongdaeoppa.demo8888888888888.dao;


import com.gongdaeoppa.demo8888888888888.dto.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {

    //@Select("SELECT id, name FROM test ORDER BY ID DESC")
    List<Test> list();

    @Insert("INSERT INTO test (name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insert(Test test);
}
