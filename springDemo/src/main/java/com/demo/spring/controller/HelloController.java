package com.demo.spring.controller;

import com.demo.spring.entity.Sale;
import com.demo.spring.entity.Student;
import com.demo.spring.service.TestService;
import com.demo.spring.util.CommonResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/spring")
public class HelloController {

    @Autowired
    private TestService testService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/hello")
    @ResponseBody
    public String index() {
        return "index";
    }

    /**
     * 测试：查询学生姓名
     * @return
     */
    @GetMapping("/name")
    @ResponseBody
    public CommonResult getName() {
        CommonResult result=new CommonResult();
        Student stu=new Student();
        stu=testService.getName();
        result.setResult(true);
        result.setObj(stu);
        return result;
    }

    /**
     * 测试：pagehelper-spring-boot-starter分页功能
     */
    @GetMapping("/sale")
    @ResponseBody
    public void getStudent(){
        int currentPage=1;
        int pageSize=10;
        //核心，设置当前页以及页面数目
        PageHelper.startPage(currentPage,pageSize);
        List<Sale> sales=testService.getSales();
        System.out.println("返回："+sales.size());
        for (Sale item:sales) {
            System.out.println(item.getDay_id()+":"+item.getMonth_id()+":"+item.getYear_id()+":"+"销售量-"+item.getSales_value());
        }
    }

    //测试redis
    @GetMapping("/redis")
    @ResponseBody
    public String testRedis(){
       stringRedisTemplate.opsForValue().set("name","long.yu");
       String name=stringRedisTemplate.opsForValue().get("name");
       return name;
    }

    /**
     * 测试 CMD 执行ORACLE数据备份
     * @author long.yu
     * @date 2019-04-15
     * @version 0.0.1
     */
    @GetMapping("/bak")
    public boolean testBak(){
        PrintWriter printWriter=null;
        BufferedReader bufferedReader=null;
        try{
            printWriter=new PrintWriter(new OutputStreamWriter(new FileOutputStream("D:/DK/orcl.dmp"),"UTF-8"));
            Process process=Runtime.getRuntime().exec("exp orcl/orcl@orcls file=D:/DK/test.dmp");
            bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                printWriter.println(line);
            }
            printWriter.flush();
            if(process.waitFor()==0){ //0表示线程正常终止
                return true;
            }
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            if (printWriter!=null){
                printWriter.close();
            }
        }
        return false;
    }
}
