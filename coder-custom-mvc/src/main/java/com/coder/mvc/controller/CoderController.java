package com.coder.mvc.controller;

import com.coder.mvc.annotation.EnjoyAutowired;
import com.coder.mvc.annotation.EnjoyController;
import com.coder.mvc.annotation.EnjoyRequestMapping;
import com.coder.mvc.annotation.EnjoyRequestParam;
import com.coder.mvc.service.CoderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnjoyController
@EnjoyRequestMapping("/coder")
public class CoderController {

    @EnjoyAutowired("CoderServiceImpl")
    private CoderService coderService;

    @EnjoyRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @EnjoyRequestParam("name") String name, @EnjoyRequestParam("age") String age){
        try (PrintWriter pw = response.getWriter();) {
            String content = coderService.Query(name,age);
            pw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
