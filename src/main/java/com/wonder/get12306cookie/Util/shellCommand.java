package com.wonder.get12306cookie.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 执行shell脚本的方法
 */
public class shellCommand {

    public static void execCommand(String cmd){
        System.out.println("cmd==========="+cmd);
        try{
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(new String[] {"/bin/sh","-c",cmd });
            InputStream stderr =  proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stderr,"GBK");
            BufferedReader br = new BufferedReader(isr);
            String line="";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (Exception e){
            System.out.println("shell脚本执行错误。");
            e.printStackTrace();
        }
    }

}
