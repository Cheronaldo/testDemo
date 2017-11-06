package com.cherry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/10/12.
 */
@Controller
@RequestMapping("/map")
public class MapController {

    @GetMapping("/show")
    public String show(){
       return "map";
       //return "location";
    }

    /**
     * 进入设备注册页面
     * @return
     */
    @GetMapping("/device")
    public String device(){
        return "device";
    }

    /**
     * 进入设备注册页面
     * @return
     */
    @GetMapping("/check")
    public String deviceCheck(){
        return "check";
    }

    @GetMapping("/photo")
    public String photoCheck(){
        return "photo";
    }


    @GetMapping("/check1")
    public String test1(){

        return "test";

    }

}
