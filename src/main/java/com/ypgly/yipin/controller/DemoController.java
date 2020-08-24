package com.ypgly.yipin.controller;

import com.ypgly.yipin.common.GeneralResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzy
 */
@RestController
public class DemoController {

    @GetMapping(value = "/getWord/say")
    @ApiOperation("测试")
    public GeneralResponse getHello(){
        return new GeneralResponse("1","成功","","hello world！");
    }
}
