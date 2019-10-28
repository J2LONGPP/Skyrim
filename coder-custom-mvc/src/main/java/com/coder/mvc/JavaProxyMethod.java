package com.coder.mvc;

import com.coder.mvc.service.impl.CoderServiceImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author long.yu
 * @date 2019-07-17
 * @desc JAVA反射代理-MVC核心
 */
public final class JavaProxyMethod {
     JavaProxyMethod() throws Exception{
        Class<?> clazz = Class.forName("com.coder.service.impl.CoderServiceImpl.class"); //类加载器加载class对象
        clazz.newInstance();//反射创建CoderServiceImpl实例
        //普通获取类实例方式
        CoderServiceImpl coderService = new CoderServiceImpl();
        Class<?> clazzp = coderService.getClass();

        Field[] fields = clazz.getDeclaredFields(); //拿到类里面定义的所有属性
        Method[] methods = clazz.getMethods(); //获取类里的所有方法
        //method.invoke(instance,args[]); //从底层调用方法，args[]方法里的参数数组
        //request.getRequestURI(); //获得请求路径
    }

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.coder.mvc.service.impl.CoderServiceImpl"); //类加载器加载class对象
            CoderServiceImpl coderService = (CoderServiceImpl) clazz.newInstance();
            System.out.println(coderService.Query("class.forName","query"));

            CoderServiceImpl coderService1 = new CoderServiceImpl();
            Class<?> clazz1 = coderService1.getClass();
            Method[] methods = clazz1.getDeclaredMethods();
            for (Method method : methods){
                System.out.println(method.invoke(coderService1,new String[]{"invoke","query"}));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
