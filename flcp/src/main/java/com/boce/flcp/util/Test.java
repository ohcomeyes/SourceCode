package com.boce.flcp.util;

import java.io.File;

/**
 * @program: flcp
 * @description: 测试
 * @author: Mr.Tang
 * @create: 2018-05-08 10:11
 **/
public class Test {

    public static void main(String[] args) {
        String path="C:/Users/tangxu/Desktop/协同设计平台模板/girl";
        File file=new File(path);
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数："+tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文件：" + tempList[i].getName());
            }
        }
    }
}
