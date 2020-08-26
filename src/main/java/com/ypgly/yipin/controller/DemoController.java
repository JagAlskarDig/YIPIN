package com.ypgly.yipin.controller;

import com.ypgly.yipin.common.GeneralResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzy
 */
@RestController
@Api(value = "测试")
public class DemoController {

    @GetMapping(value = "/getWord/say")
    @ApiOperation("测试")
    public GeneralResponse getHello(){
        return new GeneralResponse("1","成功","","hello world！");
    }

    public static void main(String[] args) {
        String str="i love you";
        System.out.println(str.length());
        String str1="我爱你！";
        System.out.println(str1.length());
    }
}
