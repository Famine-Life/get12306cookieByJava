package com.wonder.get12306cookie.Util;


import com.wonder.get12306cookie.entity.myConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
     * @return
     */
    public static boolean readAndWriteFile(String path, String tk, String R_EN, String R_DD) throws IOException {
        System.out.println("path ========="+path);
        Path filePath = Paths.get(path);
        //Path filePath = Paths.get("E:\\12306", "TickerConfig.py");
        //Path filePath2 = Paths.get("F:\\", "TickerConfig.py");    //测试路径
        try
        {
            Stream<String> lines = Files.lines(filePath);
            List<String> replaced = lines.collect(Collectors.toList());
                       //.filter(s -> !s.contains("tk =") && !s.contains("RAIL_EXPIRATION =") && !s.contains("RAIL_DEVICEID ="))

           // replaced.forEach(System.out::println);

            Map<String, String> collect = replaced.stream().filter(s -> s.contains("tk =") || s.contains("RAIL_EXPIRATION =") || s.contains("RAIL_DEVICEID ="))
                    .map(m -> m.split("=")).collect(Collectors.toMap(a -> a[0].trim(), a -> a.length > 1 ? a[1].trim() : " "));


            //collect.forEach((k,v)->System.out.println("key = " + k + " value = " + v));

            //判断tk值是否相同
            if(!collect.get("tk").contains(tk)){
                System.out.println(tk);
                System.out.println(collect.get("tk"));
                System.out.println("tk值不同,执行更新操作！");

                List<String> no_tk_list = replaced.stream().filter(s -> !s.contains("tk =") && !s.contains("RAIL_EXPIRATION =") && !s.contains("RAIL_DEVICEID ="))
                        .collect(Collectors.toList());
                no_tk_list.add("# 【最新tk cookie值更新】================:");
                no_tk_list.add("tk = \""+tk+"\"");
                no_tk_list.add("RAIL_EXPIRATION = \""+R_EN+"\"");
                no_tk_list.add("RAIL_DEVICEID = \""+R_DD+"\"");
                //写入文件
                Files.write(filePath,no_tk_list);
                //true为更新成功
                return true;
            }else {
                System.out.println(tk);
                System.out.println(collect.get("tk"));
                System.out.println("tk值相同!不执行更新操作！");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();//只是测试用例，生产环境下不要这样做异常处理
            //true为更新失败
            return false;
        }
        //true为更新失败
        return false;
    }






    public static void main(String[] args) throws IOException {
        //测试用例
        Map<String,String> map = new HashMap();
        map.put("tk","tk");
        map.put("rail_expiration","rail_expiration");
        map.put("rail_deviceid","rail_deviceid");
        fileIO.readAndWriteFile("F:\\\\TickerConfig.py",map.get("tk"),map.get("rail_expiration"),map.get("rail_deviceid"));

    }

}
