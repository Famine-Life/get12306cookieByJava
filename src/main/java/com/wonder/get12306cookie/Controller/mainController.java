package com.wonder.get12306cookie.Controller;

import com.wonder.get12306cookie.Util.fileIO;
import com.wonder.get12306cookie.Util.shellCommand;
import com.wonder.get12306cookie.entity.myConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
public class mainController {

    @Autowired
    private myConfig myConfig;

    @PostMapping("/cookie")
    public Map<String,String> getCookie(@RequestBody Map<String,String> mymap)
    {
        //log.info(mymap.get("tk"));
        String tk = mymap.get("tk");
        String rail_expiration = mymap.get("rail_expiration");
        String rail_deviceid = mymap.get("rail_deviceid");

        log.info("【接收cookie值：】");
        log.info("【tk】"+tk);
        log.info("【RAIL_EXPIRATION】"+rail_expiration);
        log.info("【RAIL_DEVICEID】"+rail_deviceid);

        Map<String,String> map = new HashMap();
        map.put("code","200");
        map.put("tk",tk);
        map.put("rail_expiration",rail_expiration);
        map.put("rail_deviceid",rail_deviceid);

        //写入文件
        fileIO.readAndWriteFile(myConfig.getTk_path(),map.get("tk"),map.get("rail_expiration"),map.get("rail_deviceid"));

        //如果是linux
        if(myConfig.isLinux==1){
            //执行shell命令 运行docker项目
            shellCommand.execCommand(myConfig.getShell_command());
        }else {
            //调用window cmd弹窗运行py文件，启动项目
            //还没学会暂时保留
            System.out.println("=========END========");
        }

        return map;
    }

    @GetMapping("/test")
    public StringBuilder getInfo(){
        StringBuilder test = new StringBuilder();
        test.append(myConfig.getTk_path());
        test.append(myConfig.getShell_command());
        test.append(myConfig.getIsLinux());

//        System.out.println(myConfig.getTk_path());
//        System.out.println(myConfig.getShell_command());
//        System.out.println(myConfig.getIsLinux());
        return test;
    }

}
