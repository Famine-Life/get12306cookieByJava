package com.wonder.get12306cookie.Util;


import com.wonder.get12306cookie.entity.myConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class fileIO {

    @Autowired
    private myConfig myConfig;

    /**
     * 读取并替换cookie的方法
     * @param tk
     * @param R_EN
     * @param R_DD
     */
    public static void readAndWriteFile(String path,String tk,String R_EN,String R_DD){
        System.out.println("path ========="+path);
        Path filePath = Paths.get(path);
        //Path filePath = Paths.get("E:\\12306", "TickerConfig.py");
        //Path filePath2 = Paths.get("F:\\", "TickerConfig.py");    //测试路径
        //try-with-resources语法,不用手动的编码关闭流
        try (Stream<String> lines = Files.lines( filePath ))
        {
            //lines.forEach(System.out::println);

//            List<String> replaced = lines
//                    .map(line-> line.replaceAll(plainTextPattern, replaceWith))
//                    .collect(Collectors.toList());

            List<String> replaced = lines
                    .filter(s -> !s.contains("tk =") && !s.contains("RAIL_EXPIRATION =") && !s.contains("RAIL_DEVICEID ="))
                    .collect(Collectors.toList());

            replaced.add("# 【最新tk cookie值更新】================:");
            replaced.add("tk = \""+tk+"\"");
            replaced.add("RAIL_EXPIRATION = \""+R_EN+"\"");
            replaced.add("RAIL_DEVICEID = \""+R_DD+"\"");
            //打印到控制台
            replaced.forEach(System.out::println);
            //写入文件
            Files.write(filePath, replaced);


        }
        catch (IOException e)
        {
            e.printStackTrace();//只是测试用例，生产环境下不要这样做异常处理
        }

    }






    public static void main(String[] args) {
        //测试用例
//        Map<String,String> map = new HashMap();
//        map.put("tk","tk");
//        map.put("rail_expiration","rail_expiration");
//        map.put("rail_deviceid","rail_deviceid");
//        fileIO.readAndWriteFile(map.get("tk"),map.get("rail_expiration"),map.get("rail_deviceid"));

    }

}
