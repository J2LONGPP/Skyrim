package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author long.yu
 * @date 2019-04-04
 * @desc 文件上传控制器
 * @version 0.0.1
 */
@RestController
@RequestMapping("/file")
public class UploadFileController {


    @Value("${uploadDir}")
    private String uploadDir;

    //使用HttpServletRequest作为参数
    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String,Object> uploadRequest(HttpServletRequest request){
        boolean flag=false;
        MultipartHttpServletRequest multipartHttpServletRequest=null;
        //强制转换为MultipartHttpServletRequest接口对象
        if(request instanceof MultipartHttpServletRequest){
            multipartHttpServletRequest= (MultipartHttpServletRequest) request;
        }else{
            return dealResultMap(false,"文件上传失败");
        }
        //获取 MultipartFile文件信息
        MultipartFile mf=multipartHttpServletRequest.getFile("file");
        //获取源文件名称
        String fileName=mf.getOriginalFilename();
        File file=new File(uploadDir+"temp.jpg");
        try {
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"文件上传失败");
        }
        return dealResultMap(true,"文件上传成功");
    }

    //使用Spring MVC的MultipartFile类作为参数
    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String,Object> uploadMultipartFile(MultipartFile file){
        String fileName=file.getOriginalFilename();
        File dest=new File(uploadDir+"temp.jpg");
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"文件上传失败");
        }
        return dealResultMap(true,"文件上传成功");
    }

    //使用 Part接口作为参数
    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String,Object> uploadPart(Part file){
        //获取提交文件名称
        String fileName=file.getSubmittedFileName();
        try {
            //写入文件
            file.write(uploadDir+"temp.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"文件上传失败");
        }
        return dealResultMap(true,"文件上传成功");
    }

    /**
     * 处理上传文件结果
     * @param success
     * @param msg
     * @return
     */
    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String,Object> result=new HashMap<>();
        result.put("success",success);
        result.put("msg",msg);
        return result;
    }
}
