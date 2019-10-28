package com.demo.spring.service;

import com.demo.spring.entity.Sale;
import com.demo.spring.entity.Student;

import java.util.List;


public interface TestService {

    /**
     * 查询学生姓名
     * @return
     */
    Student getName();

    /**
     * 查询销售情况
     * @return
     */
    List<Sale> getSales();
}
