package com.writespring.util;

/**
 *类加载util
 */
public  abstract class ClassUtils {

    public static ClassLoader classLoader(){
        ClassLoader classLoader=null;
        try{
            classLoader = Thread.currentThread().getContextClassLoader();
        }catch( Exception e){

        }
        if(classLoader==null){
            try{
                classLoader = ClassUtils.class.getClassLoader(); //如果为空，是bootstrap的类加载器
            }catch(Exception e){

            }
            if(classLoader==null){
            classLoader = ClassLoader.getSystemClassLoader();
            }
        }
       return  classLoader;
    }
}
