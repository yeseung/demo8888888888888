package com.gongdaeoppa.demo8888888888888.service;

import com.gongdaeoppa.demo8888888888888.dto.Test;
import com.gongdaeoppa.demo8888888888888.dao.TestMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService implements TestMapper {

    private final TestMapper testMapper;

    @Override
    public List<Test> list() {
        return testMapper.list();
    }

    @Override
    public Long insert(Test test) {
        testMapper.insert(test);
        return test.getId();
    }
}
