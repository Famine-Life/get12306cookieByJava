package com.wonder.get12306cookie.Controller;

import com.wonder.get12306cookie.Util.fileIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
public class mainController {

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
        fileIO.readAndWriteFile(map.get("tk"),map.get("rail_expiration"),map.get("rail_deviceid"));

        return map;
    }

    @GetMapping("/test")
    public String getInfo(){

        return "test";
    }

}
