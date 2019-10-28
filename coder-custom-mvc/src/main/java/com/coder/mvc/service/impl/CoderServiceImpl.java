package com.coder.mvc.service.impl;

import com.coder.mvc.annotation.EnjoyService;
import com.coder.mvc.service.CoderService;

@EnjoyService("CoderServiceImpl")
public class CoderServiceImpl implements CoderService {
    @Override
    public String Query(String name,String age) {
        return "name=="+name+"; age=="+age;
    }
}
