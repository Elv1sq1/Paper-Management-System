package com.tsu.zqy.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName CheckCodeUtil
 * @Author Elv1s
 * @Date 2018/12/5 18:11
 * @Description:
 */
public class CheckCodeUtil  {

    private static String word2 = "";
    private static String name = "";

    public static void makeImg() throws IOException {
        int width = 120;
        int height = 30;
        int x = 20;
        int y = 20;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //画笔对象
        Graphics g = bufferedImage.getGraphics();
        //给画笔设置颜色
        g.setColor(Color.yellow);
        //给画布上背景色
        g.fillRect(0,0,width,height);

        //给画笔再换一个颜色
        g.setColor(Color.red);
        //准备内容
        String word = "qweruorpoadjklbcmznbmv";

        Random random = new Random();

        String word3 = "";


        for (int i = 0; i < 4; i++) {
            //坐标
            int index = random.nextInt(word.length());
            char c = word.charAt(index);
            word3 += c;
            g.drawString(c+"",x,y);
            x +=20;
        }
        word2 = word3;

        //把验证码图片保存到本地
         name = UUID.randomUUID().toString()+".jpg";
        String path = "G:\\myproject\\src\\main\\resources\\static\\"+name;
        ImageIO.write(bufferedImage,"jpg",new File(path));


    }

    public static String getWord2() {
        return word2;
    }
    public static String getName() {
        return name;
    }
}
