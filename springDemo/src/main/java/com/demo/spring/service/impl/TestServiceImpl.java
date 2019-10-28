package com.demo.spring.service.impl;

import com.demo.spring.entity.Sale;
import com.demo.spring.entity.Student;
import com.demo.spring.mapper.TestMapper;
import com.demo.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TestMapper testMapper;
    @Override
    public Student getName() {
        return this.testMapper.getName();
    }

    @Override
    public List<Sale> getSales() {
        return this.testMapper.getSales();
    }
}
