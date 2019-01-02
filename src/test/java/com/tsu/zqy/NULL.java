package com.tsu.zqy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName NULL
 * @Author Elv1s
 * @Date 2018/12/5 13:01
 * @Description:
 */
public class NULL {

    @Autowired
    Person person;
    public static void main(String[] args){
       ((NULL) null).haha();
       NULL.haha();
    }

    public static void haha(){
        //System.out.println("haha");
    }

}
