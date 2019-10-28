package com.demo.spring.mapper;

import com.demo.spring.entity.Sale;
import com.demo.spring.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    /*@Select("select * from STUDENT t where t.id='1'")*/
    Student getName();

    /**
     * 查询销售情况
     * @return
     */
    List<Sale> getSales();
}
