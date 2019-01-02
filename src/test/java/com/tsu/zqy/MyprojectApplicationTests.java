package com.tsu.zqy;

import com.tsu.zqy.utils.CheckCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyprojectApplicationTests {

    @Test
    public void contextLoads() {
        Person p = new Person(18, "haha");

        System.out.println(p);
    }

    @Test
    public void checkCode() throws IOException {
        CheckCodeUtil checkCodeUtil = new CheckCodeUtil();
        checkCodeUtil.makeImg();
    }



}
