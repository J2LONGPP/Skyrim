package com.coder.mvc.servlet;

import com.coder.mvc.annotation.*;
import com.coder.mvc.controller.CoderController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatchServlet extends HttpServlet {

    //全类名路径
    List<String> classNames = new ArrayList<String>();
    //bean工厂
    Map<String,Object> beans = new HashMap<>();
    //url
    Map<String,Object> handlerMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
       this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();
        //web.xml 配置的DispatcherServlet服务名 coder-mvc
        String dispatcherName = "/coder-mvc";
        String context = req.getContextPath();
        String path = uri.replace(dispatcherName,"");

        Method method = (Method) handlerMap.get(path);
        CoderController instance = (CoderController) beans.get("/"+path.split("/")[1]);

        Object arg[] = hand(req,resp,method);
        try {
            method.invoke(instance,arg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //对参数进行处理-非策略模式-|-springMVC底层用的策略模式
    private Object[] hand(HttpServletRequest req, HttpServletResponse resp, Method method) {
        //拿到当前待执行的方法有哪些参数
        Class<?>[] paramClazzs = method.getParameterTypes();
        //根据参数的个数，new 一个参数的数组，将方法里的所有参数赋值到args来
        Object[] args = new Object[paramClazzs.length];

        int args_i = 0;
        int index = 0;
        for (Class<?> paramClazz : paramClazzs){
            if(ServletRequest.class.isAssignableFrom(paramClazz)){
                args[args_i++]= req;
            }
            if(ServletResponse.class.isAssignableFrom(paramClazz)){
                args[args_i++] = resp;
            }
            //从0-3判断有没有RequestParam注解，很明显paramClazz为0和1时，不是
            //当为2和3时为@RequestParam，需要解析
            //[@com.coder.myspring.annotation.EnjoyRequestMapping(value=name)]
            Annotation[] paramAns = method.getParameterAnnotations()[index];
            if(paramAns.length > 0){
                for(Annotation paramAn : paramAns){
                    if(EnjoyRequestParam.class.isAssignableFrom(paramAn.getClass())){
                        EnjoyRequestParam requestMapping = (EnjoyRequestParam) paramAn;
                        //找到注解里的name和age
                        args[args_i++] = req.getParameter(requestMapping.value());
                    }
                }
            }
            index++;
        }
        return args;
    }

    //GenericServlet--servlet初始化配置加载
    @Override
    public void init(ServletConfig config) throws ServletException {
        //把所有的bean扫描---扫描所有的class文件
        scanPackage("com.coder");
        //实例化bean
        doInstance();
        //依赖注入
        doIoc();
        //建立路径与方法的映射关系
        buildUrlMapping();
    }

    //扫描所有的包，并存储全类名路径
    private void scanPackage(String basePackage){
        URL url = this.getClass().getClassLoader().getResource("/"+basePackage.replaceAll("\\.","/"));
        String fileStr = url.getFile();

        File file = new File(fileStr);

        String[] filesStr = file.list();

        for (String path: filesStr){
            File filePath = new File(fileStr+path);

            if(filePath.isDirectory()){
                scanPackage(basePackage+"."+path);
            }else{
                //加入list
                classNames.add(basePackage+"."+filePath.getName());
            }
        }

    }

    //根据扫描的LIST全类名，进行实例化
    private void doInstance(){
        if(classNames.size()<=0){
            System.out.println("包扫描失败!");
            return;
        }
        //list的所有class类，对这些类进行实例化
        for(String className : classNames){
            String cn = className.replace(".class","");
            try {
                Class<?> clazz = Class.forName(cn);
                //如果扫描到@EnjoyController注解的类
                if(clazz.isAnnotationPresent(EnjoyController.class)){
                    //创建控制类
                    Object instance = clazz.newInstance();
                    EnjoyRequestMapping requestMapping = clazz.getAnnotation(EnjoyRequestMapping.class);
                    //获取@EnjoyRequestMapping拦截路径内容
                    String requestValue = requestMapping.value();
                    //把实例化的bean放入工厂
                    beans.put(requestValue,instance);
                }else if(clazz.isAnnotationPresent(EnjoyService.class)){
                    //创建服务类
                    Object instance = clazz.newInstance();
                    EnjoyService service = clazz.getAnnotation(EnjoyService.class);
                    //获取@EnjoyService内容,并将该实例化对象放入工厂
                    beans.put(service.value(),instance);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    //把service注入到controller
    private void doIoc(){
        if(beans.entrySet().size()<=0){
            System.out.println("没有一个实例化类");
            return;
        }
        //把beans工厂中的实例化遍历出来
        for(Map.Entry<String,Object> entry:beans.entrySet()){
            Object instance = entry.getValue();
            Class<?> clazz = instance.getClass();

            if(clazz.isAnnotationPresent(EnjoyController.class)){
                Field[] fields = clazz.getDeclaredFields();
                for (Field field:fields){
                    if(field.isAnnotationPresent(EnjoyAutowired.class)){
                        EnjoyAutowired autowired = field.getAnnotation(EnjoyAutowired.class);
                        String key = autowired.value();
                        //开启注入
                        field.setAccessible(true);
                        try {
                            field.set(instance,beans.get(key));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }else{
                        continue;
                    }
                }
            }
        }
    }

    private  void  buildUrlMapping(){
        if(beans.entrySet().size()<=0){
            System.out.println("没有一个实例化类");
            return;
        }
        //把beans工厂中的实例化遍历出来
        for(Map.Entry<String,Object> entry:beans.entrySet()) {
            Object instance = entry.getValue();
            Class<?> clazz = instance.getClass();

            if(clazz.isAnnotationPresent(EnjoyController.class)){
                EnjoyRequestMapping requestMapping = clazz.getAnnotation(EnjoyRequestMapping.class);
                String classPath =  requestMapping.value();

                Method[] methods = clazz.getMethods();
                for (Method method:methods){
                    if(method.isAnnotationPresent(EnjoyRequestMapping.class)){
                        EnjoyRequestMapping methodMapping = method.getAnnotation(EnjoyRequestMapping.class);
                        String methodPath = methodMapping.value();
                        handlerMap.put(classPath+methodPath,method);
                    }
                }
            }
        }
    }
}
